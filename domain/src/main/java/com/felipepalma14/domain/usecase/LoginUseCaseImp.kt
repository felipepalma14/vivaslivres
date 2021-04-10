package com.felipepalma14.domain.usecase

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.data.model.Country
import com.felipepalma14.data.model.LogInFailedState
import com.felipepalma14.data.repository.LoginRepository
import com.felipepalma14.data.repository.PhoneCallbacksListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

class LoginUseCaseImp (
    private val repository: LoginRepository
) : LoginUseCase {

    override fun setPhoneCallbacksListener(listener: PhoneCallbacksListener){
        repository.setPhoneCallbacksListener(listener)
    }
    override fun sendOtpToPhone(country: Country, phone: String, activity: Activity){
        repository.sendVerificationCode(country,phone, activity)
    }

    override fun verifyOtp(otp: String): PhoneAuthCredential {
        return repository.verifyVerificationCode(otp)
    }

    override fun resendOtp(country: Country, phone: String, activity: Activity){
        repository.resendCode(country, phone, activity)
    }

    override fun getVerificationId(): MutableLiveData<String> {
        return repository.getVerificationId()
    }

    override fun setMobile(country: Country, mobile: String) {
        TODO("Not yet implemented")
    }


    override fun getCredential(): LiveData<PhoneAuthCredential> {
        TODO("Not yet implemented")
    }

    override fun setCredential(credential: PhoneAuthCredential) {
        TODO("Not yet implemented")
    }

    override fun setVerificationCodeNull() {
        TODO("Not yet implemented")
    }


    override fun getTaskResult(): LiveData<Task<AuthResult>> {
        TODO("Not yet implemented")
    }

    override fun getFailed(): LiveData<LogInFailedState> {
        TODO("Not yet implemented")
    }



    override fun clearOldAuth() {
        TODO("Not yet implemented")
    }


}