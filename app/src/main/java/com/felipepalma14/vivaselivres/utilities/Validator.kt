package com.felipepalma14.vivaselivres.utilities

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

object Validator {

    fun isPhoneNumberValid(code: String, phoneNumber: String): Boolean {
        try {
            val phoneUtil = PhoneNumberUtil.getInstance()
            val phNumberProto: Phonenumber.PhoneNumber = phoneUtil.parse(
                phoneNumber, code
            )
            return phoneUtil.isValidNumber(phNumberProto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun isMobileNumberEmpty(phoneNumber: String?): Boolean{
        return phoneNumber.isNullOrEmpty()
    }

}