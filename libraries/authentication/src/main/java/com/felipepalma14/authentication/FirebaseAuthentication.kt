package com.felipepalma14.authentication

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.libraries.core.database.model.Country
import com.felipepalma14.libraries.core.state.LogInFailedState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import timber.log.Timber
import java.util.concurrent.TimeUnit

class FirebaseAuthentication(
    private val authenticator: FirebaseAuth = FirebaseAuth.getInstance(),
    private val activity: Activity,
    private val phoneCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks

): IAuthenticator {
    private val credential: MutableLiveData<PhoneAuthCredential> = MutableLiveData()

    private val taskResult: MutableLiveData<Task<AuthResult>> = MutableLiveData()

    private val failedState: MutableLiveData<LogInFailedState> = MutableLiveData()

    fun setMobile(country: Country, mobile: String) {
        Timber.v("Mobile $mobile")
        val number = country.noCode + " " + mobile
        val options = PhoneAuthOptions.newBuilder(authenticator)
            .setPhoneNumber(number)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(phoneCallback)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        authenticator.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Timber.v("signInWithCredential:success")
                    taskResult.value = task
                } else {
                    Timber.v("signInWithCredential:failure ${task.exception}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException)
                    failedState.value = LogInFailedState.SignIn
                }
            }
    }

    fun setCredential(credential: PhoneAuthCredential) {
        signInWithPhoneAuthCredential(credential)
    }

    fun clearOldAuth(){
        credential.value = null
        taskResult.value = null
    }

    fun getCredential(): LiveData<PhoneAuthCredential> {
        return credential
    }

    fun getTaskResult(): LiveData<Task<AuthResult>> {
        return taskResult
    }

    fun getFailed(): LiveData<LogInFailedState> {
        return failedState
    }

    override fun signOut() {
        authenticator.signOut()
    }

    override fun isSignedIn() = authenticator.currentUser != null

    override fun getCurrentUser(): IUser? {
        return null
    }
}