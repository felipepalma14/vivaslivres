package com.felipepalma14.vivaselivres.utilities

import android.app.Activity
import android.view.View
import android.widget.ProgressBar
import androidx.navigation.NavController
import com.felipepalma14.vivaselivres.R
import com.felipepalma14.vivaselivres.extension.gone
import com.felipepalma14.vivaselivres.extension.show
import com.felipepalma14.vivaselivres.ui.custom.CustomProgressView
import com.google.android.material.snackbar.Snackbar


fun NavController.isValidDestination(destination: Int): Boolean {
    return destination == this.currentDestination!!.id
}

fun snack(context: Activity, msg: String){
    Snackbar.make(context.findViewById(android.R.id.content),msg,2000).show()
}

fun snackNet(context: Activity){
    Snackbar.make(context.findViewById(android.R.id.content), R.string.err_no_net,2000).show()
}

fun View.hide(){
    this.visibility=View.INVISIBLE
}

fun CustomProgressView.toggle(show: Boolean){
    if (show)
        this.show()
    else
        this.dismiss();
}

fun CustomProgressView.dismissIfShowing(){
    if (this.isShowing)
        this.dismiss()
}

fun ProgressBar.toggle(show: Boolean){
    if (show)
        this.show()
    else
        this.gone();
}