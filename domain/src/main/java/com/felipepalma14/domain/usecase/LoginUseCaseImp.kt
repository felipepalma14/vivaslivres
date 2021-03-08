package com.felipepalma14.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.felipepalma14.domain.model.Country
import com.felipepalma14.domain.model.LogInFailedState
import com.felipepalma14.domain.repository.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.PhoneAuthCredential

class LoginUseCaseImp(
    private val repository: LoginRepository
) : LoginUseCase{

    override fun getCredential(): LiveData<PhoneAuthCredential> {
        return repository.getCredential()
    }

    override fun setCredential(credential: PhoneAuthCredential) {
        //setVProgress(true)
        repository.setCredential(credential)
    }
    override fun setVerificationCodeNull(){
        //verifyCode = repository.getVerificationCode().value!!
        //repository.setVerificationCodeNull()
    }

    override fun getVerificationId(): MutableLiveData<String> {
        return repository.getVerificationCode()
    }

    override fun getTaskResult(): LiveData<Task<AuthResult>> {
        return repository.getTaskResult()
    }

    override fun getFailed(): LiveData<LogInFailedState> {
        return repository.getFailed()
    }

    override fun setMobile(country: Country, mobile: String) {
        TODO("Not yet implemented")
    }

    override fun clearOldAuth() {
        TODO("Not yet implemented")
    }

}