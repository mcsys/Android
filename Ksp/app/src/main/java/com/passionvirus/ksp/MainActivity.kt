package com.passionvirus.ksp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        KSPSampleLogger.sendEvent(
            20,
            "asfasf"
        )

    }


    fun test() {

        /*
        firebaseAnalytics.logEvent("share_image") {
            param("image_name", "asdasdf")
            param("full_text", "asda")
        }

        val bundle = Bundle()
        bundle.apply {
            putInt("aaa", 1)
        }
        firebaseAnalytics.logEvent("name", bundle)
        */

    }
}