package com.felipepalma14.data.repository

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.data.model.Country
import com.felipepalma14.data.model.LogInFailedState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

interface LoginRepository {

    fun getVerificationId() : MutableLiveData<String>

    fun setPhoneCallbacksListener(listener: PhoneCallbacksListener)

    fun sendVerificationCode(country: Country, phone: String, activity: Activity)

    fun verifyVerificationCode(code: String): PhoneAuthCredential

    fun resendCode(country: Country, phone: String, activity: Activity)

    fun setMobile(country: Country, mobile: String)

    fun getCredential(): LiveData<PhoneAuthCredential>

    fun setCredential(credential: PhoneAuthCredential)

    fun setVerificationCodeNull()

    fun getVerificationCode(): MutableLiveData<String>

    fun getTaskResult(): LiveData<Task<AuthResult>>

    fun getFailed(): LiveData<LogInFailedState>

    fun clearOldAuth()
}