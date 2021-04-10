package com.felipepalma14.vivaselivres.base

import androidx.lifecycle.ViewModel

open class BaseViewModel<VI: ViewInteractor>: ViewModel() {

    var viewInteractor: VI? = null


}