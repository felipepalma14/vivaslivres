package com.felipepalma14.vivaselivres.ui.login.presentation

import android.app.Activity
import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.data.repository.PhoneCallbacksListener
import com.felipepalma14.data.model.Country
import com.felipepalma14.domain.usecase.LoginUseCase
import com.felipepalma14.vivaselivres.base.BaseViewModel
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import javax.inject.Singleton

@ActivityScoped
@Singleton
class LoginViewModel @ViewModelInject
constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<AuthViewInteractor>(), PhoneCallbacksListener {

    companion object {
        const val PHONE_VERIFICATION_PAGE   = 0
        const val OTP_VERIFICATION_PAGE     = 1
        private const val RESEND_WAIT_MILLIS: Long = 30000
        private const val TICK_INTERVAL_MILLIS: Long = 1000
    }

    init {
        loginUseCase.setPhoneCallbacksListener(this)
        /*
        if (fireBaseAuthProvider.isUserVerified()) {
            viewInteractor?.goToGoalActivity()
        }*/
    }

    val country = MutableLiveData<Country>()
    val mobile = MutableLiveData<String>()
    var ediPosition = 0

    var verifyCode: String = ""
    private var phone : String = ""
    private val progress = MutableLiveData(false)

    private lateinit var timer: CountDownTimer


    fun sendOtpToPhone(country: Country, phone: String, activity: Activity) {
        this.phone = phone
        viewInteractor?.startSMSListener()
        loginUseCase.sendOtpToPhone(country, phone, activity)
    }

    fun verifyOtp(otp: String) {
        viewInteractor?.signInWithPhoneAuthCredential(loginUseCase.verifyOtp(otp))
    }

    fun resendOtp(country: Country, phone: String, activity: Activity) {
        viewInteractor?.startSMSListener()
        loginUseCase.resendOtp(country, phone, activity)
        //resetCountdownTick()
    }




    fun getVerificationId(): MutableLiveData<String>{
        return loginUseCase.getVerificationId()
    }


    fun setProgress(show: Boolean) {
        progress.value = show
    }

    fun getProgress(): LiveData<Boolean> {
        return progress
    }

    fun setCountry(country: Country) {
        this.country.value = country
    }

    fun setMobile() {
        //loginUseCase.clearOldAuth()
        //saveMobile()
        //loginUseCase.setMobile(country.value!!, mobile.value!!)
    }

    fun saveMobile(){

    }

    override fun onVerificationCompleted() {
        viewInteractor?.showSnackBarMessage("Verification Completed")
        viewInteractor?.goToGoalActivity()
    }

    override fun onVerificationCodeDetected(code: String) {
        Timber.d("AuthActivityViewModel onReceive: success $code")
    }

    override fun onVerificationFailed(message: String) {
        Timber.d(message)
        viewInteractor?.showSnackBarMessage(message)
    }

    override fun onCodeSent(
        verificationId: String?,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        viewInteractor?.showSnackBarMessage("OTP has sent")
        //pagerPagePosition.value = OTP_VERIFICATION_PAGE
    }
}