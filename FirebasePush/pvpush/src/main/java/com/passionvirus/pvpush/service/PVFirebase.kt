package com.passionvirus.pvpush.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.support.annotation.NonNull
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import com.passionvirus.pvutils.AppPreferences

const val PVFIREBASE_NOTIFICATION_CHANNEL_ID             = "PVFIREBASE_NOTIFICATION_CHANNEL_ID"
const val PVFIREBASE_NOTIFICATION_CHANNEL_NAME           = "PVFIREBASE_NOTIFICATION_CHANNEL_NAME"
const val PVFIREBASE_NOTIFICATION_CHANNEL_IMPORTANCE     = "PVFIREBASE_NOTIFICATION_CHANNEL_IMPORTANCE"
const val PVFIREBASE_NOTIFICATION_CONTENT_TITLE          = "PVFIREBASE_NOTIFICATION_CONTENT_TITLE"
const val PVFIREBASE_NOTIFICATION_CONTENT_TEXT           = "PVFIREBASE_NOTIFICATION_CONTENT_TEXT"
const val PVFIREBASE_NOTIFICATION_INTENT_CLASS_NAME      = "PV_FIREBASE_INTENT_CLASS_NAME"
const val PVFIREBASE_NOTIFICATION_APP_NAME               = "PVFIREBASE_NOTIFICATION_APP_NAME"
const val PVFIREBASE_NOTIFICATION_ICON                   = "PVFIREBASE_NOTIFICATION_ICON"
const val PVFIREBASE_NOTIFICATION_SMALL_ICON             = "PVFIREBASE_NOTIFICATION_SMALL_ICON"
const val PVFIREBASE_NOTIFICATION_LARGE_ICON             = "PVFIREBASE_NOTIFICATION_LARGE_ICON"
const val PVFIREBASE_NOTIFICATION_ICON_CIRCLE_COLOR      = "PVFIREBASE_NOTIFICATION_ICON_CIRCLE_COLOR"
const val PVFIREBASE_NOTIFICATION_RINGTON_SOUND_URI      = "PVFIREBASE_NOTIFICATION_RINGTON_SOUND_URI"
const val PVFIREBASE_NOTIFICATION_AUTO_CANCEL            = "PVFIREBASE_NOTIFICATION_AUTO_CANCEL"


class PVFirebaseNotification {

    private lateinit var context: Context
//    private lateinit var notificationManager: NotificationManager

    fun Builder(c: Context) {
        context = c
    }

    /*
    fun getIntentClass() : String {
        return AppPreferences(context).getPrefString(PV_FIREBASE_INTENT_CLASS_NAME)
    }

    fun setIntentClass(@NonNull default: String) {
        AppPreferences(context).setPrefString(PV_FIREBASE_INTENT_CLASS_NAME, default)
    }
    */

    fun getChannelId() : String {
        return AppPreferences(context).getPrefString(PVFIREBASE_NOTIFICATION_CHANNEL_ID)
    }

    fun setChannelId(id : String) {
        AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_CHANNEL_ID, id)
    }

    fun getChannelName() : String {
        return AppPreferences(context).getPrefString(PVFIREBASE_NOTIFICATION_CHANNEL_NAME)
    }

    fun setChannelName(name : String) {
        AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_CHANNEL_NAME, name)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getChannelImportance() : Int {
        return AppPreferences(context).getPrefInt(PVFIREBASE_NOTIFICATION_CHANNEL_IMPORTANCE, NotificationManager.IMPORTANCE_LOW)
    }

    fun setChannelImportance(importance : Int) {
        AppPreferences(context).setPrefInt(PVFIREBASE_NOTIFICATION_CHANNEL_IMPORTANCE, importance)
    }

    fun getContentTitle() : String {
        return AppPreferences(context).getPrefString(PVFIREBASE_NOTIFICATION_CONTENT_TITLE)
    }

    fun setContentTitle(default : String) {
        return AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_CONTENT_TITLE, default)
    }

    fun getContentText() : String {
        return AppPreferences(context).getPrefString(PVFIREBASE_NOTIFICATION_CONTENT_TEXT)
    }

    fun setContentText(default : String) {
        return AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_CONTENT_TEXT, default)
    }

    /*
    fun getAppName(): String {
        return AppPreferences(context).getPrefString(PVFIREBASE_NOTIFICATION_APP_NAME)
    }

    fun setAppName(@NonNull default: String) {
        AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_APP_NAME, default)
    }
    */

    fun getIcon(): Int {
        return AppPreferences(context).getPrefInt(PVFIREBASE_NOTIFICATION_ICON)
    }

    fun setIcon(@NonNull default: Int) {
        AppPreferences(context).setPrefInt(PVFIREBASE_NOTIFICATION_ICON, default)
    }

    fun getSmallIcon(): Int {
        return AppPreferences(context).getPrefInt(PVFIREBASE_NOTIFICATION_SMALL_ICON)
    }

    fun setSmallIcon(@NonNull default: Int) {
        AppPreferences(context).setPrefInt(PVFIREBASE_NOTIFICATION_SMALL_ICON, default)
    }

    // Bitmap type
    /*
    fun getLargeIcon(): Int {
        return AppPreferences(context).getPrefInt(PVFIREBASE_NOTIFICATION_LARGE_ICON)
    }

    fun setLargeIcon(@NonNull default: Int) {
        AppPreferences(context).setPrefInt(PVFIREBASE_NOTIFICATION_LARGE_ICON)
    }
    */

    fun getIconCircleColor(): Int {
        return AppPreferences(context).getPrefInt(PVFIREBASE_NOTIFICATION_ICON_CIRCLE_COLOR)
    }

    fun setIconCircleColor(@NonNull default: Int) {
        AppPreferences(context).setPrefInt(PVFIREBASE_NOTIFICATION_ICON_CIRCLE_COLOR, default)
    }

    fun getRingtonSoundUri() : Uri {
        return Uri.parse(AppPreferences(context).getPrefString(PVFIREBASE_NOTIFICATION_RINGTON_SOUND_URI))
    }

    fun setRingtonSoundUri(uri : Uri) {
        AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_RINGTON_SOUND_URI, uri.toString())
    }

    fun setRingtonSoundUri(uri : String) {
        AppPreferences(context).setPrefString(PVFIREBASE_NOTIFICATION_RINGTON_SOUND_URI, uri)
    }

    fun getAutoCancel(): Boolean {
        return AppPreferences(context).getPrefBoolean(PVFIREBASE_NOTIFICATION_AUTO_CANCEL)
    }

    fun setAutoCancel(auto: Boolean) {
        AppPreferences(context).setPrefBoolean(PVFIREBASE_NOTIFICATION_AUTO_CANCEL, auto)
    }

    fun show(cls : Class<*>) {
        val channelId = getChannelId()
        val channelName = getChannelName()

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

//        val intent = Intent(context, MainActivity::class.java)
        val intent = Intent(context, cls)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(context, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)


        val notificationBuilder = NotificationCompat.Builder(context, channelId)

        getContentTitle().apply {
            notificationBuilder.setContentTitle(this)
        }

        getContentTitle().apply {
            notificationBuilder.setContentText(this)
        }

        getSmallIcon().apply {
            notificationBuilder.setSmallIcon(this)
        }

        // Bitmap type
        /*
        getLargeIcon().apply {
            notificationBuilder.setLargeIcon(this)
        }
        */

//        getIconCircleColor.apply {
//            notificationBuilder.setColor()
//        }

        getRingtonSoundUri().apply {
            notificationBuilder.setSound(this)
        }

        getAutoCancel().apply {
            notificationBuilder.setAutoCancel(this)
        }


        // Todo Add more options

        notificationBuilder.setContentIntent(pendingIntent)


        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelImportance = getChannelImportance()

            val channel = NotificationChannel(channelId,
                    channelName,
                    channelImportance)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
        }
    }

}