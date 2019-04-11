package com.passionvirus.productivesample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.passionvirus.productivesample.service.MyIntentService
import com.passionvirus.productivesample.service.MyService

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)


    }

    fun onStartService(v: View) {
        Log.d("TEST1234", "onStartService")
        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    fun onStopService(v: View) {
        Log.d("TEST1234", "onStopService")
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }

    fun onIntentService(v: View) {
        Log.d("TEST1234", "onBindService")
        val intent = Intent(this, MyIntentService::class.java)
        startService(intent)
    }
}