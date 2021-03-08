package com.felipepalma14.vivaselivres

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppVivasELivres : MultiDexApplication() {

    companion object {
        lateinit var instance: AppVivasELivres
            private set
        var isAppRunning = false
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = this
        //FirebaseApp.initializeApp(this)
        setupTimber()

    }


    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "Vivas e Livres/${element.fileName}:${element.lineNumber})#${element.methodName}"
                }
            })
        }
    }
}