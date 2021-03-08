package com.felipepalma14.data.repository

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.domain.model.Country
import com.felipepalma14.domain.model.LogInFailedState
import com.felipepalma14.domain.repository.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    @ActivityRetainedScoped val activity: Activity,
    @ApplicationContext val context: Context,
    val firebaseAuth: FirebaseAuth
): LoginRepository {

    private val verificationId: MutableLiveData<String> = MutableLiveData()

    private val credential: MutableLiveData<PhoneAuthCredential> = MutableLiveData()

    private val taskResult: MutableLiveData<Task<AuthResult>> = MutableLiveData()

    private val failedState: MutableLiveData<LogInFailedState> = MutableLiveData()

    private lateinit var phoneCallbacksListener:PhoneCallbacksListener

    fun setPhoneCallbacksListener(listener: PhoneCallbacksListener) {
        this.phoneCallbacksListener = listener
    }

    init {
        firebaseAuth.setLanguageCode(Locale.getDefault().language)
    }

    private val callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                val code = phoneAuthCredential.smsCode
                if (code != null) phoneCallbacksListener.onVerificationCodeDetected(code)

                Timber.v("onVerificationCompleted:$credential")
                credential.value = phoneAuthCredential
                Handler(Looper.getMainLooper()).postDelayed({
                    signInWithPhoneAuthCredential(phoneAuthCredential)
                }, 1000)

            }

            override fun onVerificationFailed(e: FirebaseException) {
                failedState.value = LogInFailedState.Verification
                when (e) {
                    is FirebaseAuthInvalidCredentialsException -> {
                        // Invalid request
                        phoneCallbacksListener.onVerificationFailed(e.message?:" ")
                    }
                    is FirebaseTooManyRequestsException -> {
                        // The SMS quota for the project has been exceeded
                        phoneCallbacksListener.onVerificationFailed(e.message?:" ")
                    }
                    else -> {
                        phoneCallbacksListener.onVerificationFailed(e.message?:" ")
                    }
                }
                Timber.d("FireBaseAuthProvider.onVerificationFailed e() ${e.message}")
            }

            override fun onCodeSent(s: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, forceResendingToken)
                verificationId.value = s
                phoneCallbacksListener.onCodeSent(s, forceResendingToken)
            }
        }

    override fun setMobile(country: Country, mobile: String) {
        Timber.v("Mobile $mobile")
        val number = country.noCode + " " + mobile
        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.v("signInWithCredential:success")
                    taskResult.value = task
                } else {
                    Timber.v("signInWithCredential:failure ${task.exception}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException)
                        //context.toast("Invalid verification code!")
                    failedState.value = LogInFailedState.SignIn
                }
            }
    }

    fun isUserVerified(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun setCredential(credential: PhoneAuthCredential) {
        signInWithPhoneAuthCredential(credential)
    }

    override fun getVerificationCode(): MutableLiveData<String> {
        return verificationId
    }

    override fun setVerificationCodeNull() {
        verificationId.value = null
    }

    override fun clearOldAuth(){
        credential.value=null
        taskResult.value=null
    }

    override fun getCredential(): LiveData<PhoneAuthCredential> {
        return credential
    }

    override fun getTaskResult(): LiveData<Task<AuthResult>> {
        return taskResult
    }

    override fun getFailed(): LiveData<LogInFailedState> {
        return failedState
    }
}

interface PhoneCallbacksListener {
    fun onVerificationCompleted()
    fun onVerificationCodeDetected(code: String)
    fun onVerificationFailed(message: String)
    fun onCodeSent(
        verificationId: String?,
        token: PhoneAuthProvider.ForceResendingToken?
    )
}