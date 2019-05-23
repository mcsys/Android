package com.passionvirus.sample.ui

import android.app.Application
import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics

class Analytics {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    companion object {
        private lateinit var sInstance: Analytics

        fun getInstance(context: Context): Analytics {
            if(!::sInstance.isInitialized) {
                sInstance = Analytics()
            }

            return sInstance
        }
    }

    fun init(application: Application) {
        sInstance.mFirebaseAnalytics = FirebaseAnalytics.getInstance(application)
    }

    fun sendLog() {
        mFirebaseAnalytics?.logEvent("test_send", null)
    }



}

