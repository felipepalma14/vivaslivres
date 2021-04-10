package com.felipepalma14.vivaselivres.ui.login.presentation

import com.felipepalma14.vivaselivres.base.ViewInteractor
import com.google.firebase.auth.PhoneAuthCredential

interface AuthViewInteractor  : ViewInteractor {
    fun showSnackBarMessage(message: String)

    fun goToGoalActivity()

    fun startSMSListener()

    fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential)
}