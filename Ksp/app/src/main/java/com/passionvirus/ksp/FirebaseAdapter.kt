package com.passionvirus.ksp

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase

object FirebaseAdapter {
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    var eventName = "ksp"


    fun sendEvent(name: String = "") {
        if (name.isNotEmpty()) {
            eventName = name
        }
        firebaseAnalytics.logEvent(eventName) {
            param("event", "s")
        }
    }
}