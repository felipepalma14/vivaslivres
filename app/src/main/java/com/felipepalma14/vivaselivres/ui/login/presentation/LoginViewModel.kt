package com.felipepalma14.vivaselivres.ui.login.presentation

import android.content.Context
import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipepalma14.domain.model.Country
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@ActivityScoped
@Singleton
class LoginViewModel @ViewModelInject
constructor(
    @ApplicationContext private val context: Context
    //,
    //private val usecase: LoginUseCase
) : ViewModel() {

    val country = MutableLiveData<Country>()
    val mobile = MutableLiveData<String>()
    var ediPosition = 0

    var verifyCode: String = ""

    private val progress = MutableLiveData(false)

    private lateinit var timer: CountDownTimer


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
        //usecase.clearOldAuth()
        saveMobile()
        //usecase.setMobile(country.value!!, mobile.value!!)
    }

    fun saveMobile(){

    }
}