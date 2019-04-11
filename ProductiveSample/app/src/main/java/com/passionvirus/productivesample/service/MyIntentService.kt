package com.passionvirus.productivesample.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntent") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d(MyIntentService::class.java.name, "onHandleIntent")
    }

}