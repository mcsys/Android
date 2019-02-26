package com.passionvirus.productivesample

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Add LeakCanary
        /*
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }

        LeakCanary.install(this)
        */

        // Steotho
        Stetho.initializeWithDefaults(this)
    }
}