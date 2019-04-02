package com.passionvirus.productivesample

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

const val CUSTOM_ACTION  = "com.passionvirus.productivesample.action.ACTION_MY_BROADCAST"

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "Battery Low...Charging Please", Toast.LENGTH_LONG).show()
        } else if (intent?.action.equals(CUSTOM_ACTION)) {
            Toast.makeText(context, "Received Custom BroadCast", Toast.LENGTH_SHORT).show()
        }
    }
}