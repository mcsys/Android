package com.passionvirus.pvpush.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PVFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: ${remoteMessage?.from}")
        Log.d(TAG, "onMessageReceived :: PVFirebaseMessagingService")
        Log.d("TEST1234", "From: ${remoteMessage?.from}")
        Log.d("TEST1234", "onMessageReceived")

        // Check if message contains a data payload.
        remoteMessage?.data?.isNotEmpty()?.let {
            Log.d("TEST1234", "Message data payload: " + remoteMessage.data)

            if (/* Check if data needs to be processed by long running job */ true) {
//                scheduleJob()
            } else {
                handleNow()
            }
        }

        remoteMessage?.notification?.let {
            Log.d("TEST1234", "Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String?) {
        Log.d("TEST1234", "Refreshed token: $token")

        sendRegistrationToServer(token)
    }

    private fun scheduleJob() {
        // Something
        /*
        val dispatcher = FirebaseJobDispatcher(GooglePlayDriver(this))
        val myJob = dispatcher.newJobBuilder()
                .setService(MyJobService::class.java)
                .setTag("my-job-tag")
                .build()
        dispatcher.schedule(myJob)
        */
    }

    private fun handleNow() {
        Log.d(TAG, "Short lived task is done.")
    }

    private fun sendRegistrationToServer(token: String?) {
        // Implement this method to send token to your app server.
    }

    private fun sendNotification(messageBody: String) {
        Log.d("TEST1234", "sendNotification")
        /*
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle(getString(R.string.fcm_message))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
        */
    }

    companion object {
//        private val TAG = PVFirebaseMessagingService::class.simpleName
        private val TAG = "PVFMS"
    }
}