package com.passionvirus.sample.sample.tddmvvm

import android.app.Application

class BaseApplication : Application() {
    private val appExcutor = AppExecutors()
    override fun onCreate() {
        super.onCreate()

    }
}