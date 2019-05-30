package com.passionvirus.productivesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.app.Notification
import android.app.NotificationManager
import android.app.NotificationChannel
import android.media.AudioAttributes
import android.support.v4.app.NotificationCompat
import android.app.PendingIntent
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri


class NotificationActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sendMyNotification("TEST")
    }

    private fun sendMyNotification(message: String) {

        val channelId = "notification_channel_id"
        val channelName = "notification_channel_name"
        val intent = Intent(this, Notification::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val soundUri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" +
                    packageName +
                    "/" +
                    R.raw.push_sound
        )

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(message)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            if (soundUri != null) {
                // Changing Default mode of notification
                notificationBuilder.setDefaults(Notification.DEFAULT_ALL)
                // Creating an Audio Attribute
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                // Creating Channel
                val notificationChannel =
                    NotificationChannel(channelId, channelIdName, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.setSound(soundUri, audioAttributes)
                mNotificationManager.createNotificationChannel(notificationChannel)
            }
        }
        mNotificationManager.notify(0, notificationBuilder.build())
    }

}