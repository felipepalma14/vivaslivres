package com.felipepalma14.domain.usecase

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.data.repository.PhoneCallbacksListener
import com.felipepalma14.data.model.Country
import com.felipepalma14.data.model.LogInFailedState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

interface LoginUseCase {
    fun setPhoneCallbacksListener(listener: PhoneCallbacksListener)

    fun sendOtpToPhone(country: Country, phone: String, activity: Activity)

    fun verifyOtp(otp: String) : PhoneAuthCredential

    fun resendOtp(country: Country, phone: String, activity: Activity)

    fun getCredential(): LiveData<PhoneAuthCredential>

    fun setCredential(credential: PhoneAuthCredential)

    fun setVerificationCodeNull()

    fun getVerificationId(): MutableLiveData<String>

    fun getTaskResult(): LiveData<Task<AuthResult>>

    fun getFailed(): LiveData<LogInFailedState>

    fun setMobile(country: Country, mobile: String)

    fun clearOldAuth()

}