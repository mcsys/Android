package com.passionvirus.ksp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        KSPSampleLogger.sendEvent(
            age = 20,
            nameKSPSample = "sss"
        )
    }
}