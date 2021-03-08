package com.felipepalma14.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.domain.model.Country
import com.felipepalma14.domain.model.LogInFailedState
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

interface LoginRepository {
    fun setMobile(country: Country, mobile: String)

    fun getCredential(): LiveData<PhoneAuthCredential>

    fun setCredential(credential: PhoneAuthCredential)

    fun setVerificationCodeNull()

    fun getVerificationCode(): MutableLiveData<String>

    fun getTaskResult(): LiveData<Task<AuthResult>>

    fun getFailed(): LiveData<LogInFailedState>

    fun clearOldAuth()
}