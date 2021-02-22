package com.felipepalma14.libraries.core.extension

import android.view.View

fun View.gone(){
    this.visibility=View.GONE
}

fun View.show(){
    this.visibility=View.VISIBLE
}