package com.passionvirus.pvpush.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.content.LocalBroadcastManager
import android.text.TextUtils
import android.util.Log
import java.util.*

object SSPush {

    /*
    fun setIntentClass(c: Context, s: String) {
        SSPushPref.setPref(c, SSPushPref.PREF_TARGETINTENT, s)
    }

    fun getIntentClass(c: Context): String {
        return SSPushPref.getPref(c, SSPushPref.PREF_TARGETINTENT)
    }

    fun setAppName(c: Context, s: String) {
        SSPushPref.setPref(c, SSPushPref.PREF_NOTICENTER_APPNAME, s)
    }

    fun getAppName(c: Context): String {
        return SSPushPref.getPref(c, SSPushPref.PREF_NOTICENTER_APPNAME)
    }

    fun getIconResVal(c: Context): Int {
        return SSPushPref.getPrefInt(c, SSPushPref.PREF_NOTICENTER_ICON, 0)
    }

    fun setIconResVal(c: Context, r: Int) {
        SSPushPref.setPrefInt(c, SSPushPref.PREF_NOTICENTER_ICON, r)
    }

    fun getSmallIconResVal(c: Context): Int {
        return SSPushPref.getPrefInt(c, SSPushPref.PREF_NOTICENTER_SMALL_ICON, 0)
    }

    fun setSmallIconResVal(c: Context, r: Int) {
        SSPushPref.setPrefInt(c, SSPushPref.PREF_NOTICENTER_SMALL_ICON, r)
    }

    fun getUseLargeAndSmallIconBoth(c: Context): Boolean {
        return SSPushPref.getPrefBool(c, SSPushPref.PREF_NOTICENTER_USE_LARGE_AND_SMALL_ICON, false)
    }

    /**
     * 노티화면에 큰아이콘과 작은아이콘을 동시에 사용할지 결정합니다.게임에서 주로 사용합니다.
     * @param c
     * @param useBoth
     */
    fun setUseLargeAndSmallIconBoth(c: Context, useBoth: Boolean) {
        SSPushPref.setPrefBool(c, SSPushPref.PREF_NOTICENTER_USE_LARGE_AND_SMALL_ICON, useBoth)
    }

    @Deprecated("")
    fun getBigPicResVal(c: Context): Int {
        return SSPushPref.getPrefInt(c, SSPushPref.PREF_BIGPIC_RESID, 0)
    }

    /**
     * setBigPicResVal 는 기본 BigPic의 ResourceID를 지정합니다. Application의 OnCreate에서 설정해줘야합니다.
     * 되도록 setBigPicUriPathForDefault 를 이용해주세요.
     * @param c
     * @param r
     */
    @Deprecated("")
    fun setBigPicResVal(c: Context, r: Int) {
        SSPushPref.setPref(c, SSPushPref.PREF_BIGPIC_URI, "")
        SSPushPref.setPrefInt(c, SSPushPref.PREF_BIGPIC_RESID, r)
    }


    fun getBigPicUriForDefault(c: Context): String {
        return SSPushPref.getPref(c, SSPushPref.PREF_BIGPIC_URI)
    }

    fun setBigPicUriPathForDefault(c: Context, uri: String) {
        SSPushPref.setPrefInt(c, SSPushPref.PREF_BIGPIC_RESID, 0)
        SSPushPref.setPref(c, SSPushPref.PREF_BIGPIC_URI, uri)
    }


    fun getIconCircleColorVal(c: Context): Int {
        return SSPushPref.getPrefInt(c, SSPushPref.PREF_NOTICENTER_ICON_CIRCLE_COLOR, DEFAULT_NOTICENTER_ICON_CIRCLE_COLOR)
    }

    fun setIconCircleColorVal(c: Context, argb: Int) {
        SSPushPref.setPrefInt(c, SSPushPref.PREF_NOTICENTER_ICON_CIRCLE_COLOR, argb)
    }


    internal fun setPushClientClassName(c: Context, className: String) {
        if (SSPushPref.setPref(c, SSPushPref.PREF_PUSHCLIENT_CLASSNAME, className)) {
            SSPushPref.setPrefBool(c, SSPushPref.PREF_DIRTY, true)
        }
    }

    internal fun getPushClientClassName(c: Context): String {
        return SSPushPref.getPref(c, SSPushPref.PREF_PUSHCLIENT_CLASSNAME)
    }

    internal fun initBaseInfoPrefs(c: Context, cmsId: String) {
        var l: Locale? = Locale.getDefault()
        if (l == null)
            l = c.resources.configuration.locale

        if (l == null)
            return

        val tz = TimeZone.getDefault() ?: return

        val deviceId = SSUDID.getSSUDID(c)
        val appVersion = SSWebLog.getVersionName(c)
        var language = l.language.toLowerCase(Locale.US)
        val country = l.country.toLowerCase(Locale.US)

        if (language == "zh") {
            if (country.equals("CN", ignoreCase = true)) {
                language += "-Hans"
            } else if (country.equals("TW", ignoreCase = true)) {
                language += "-Hant"
            } else if (country.equals("HK", ignoreCase = true)) {
                language += "-hk"
            }
        }

        val timeZone = (tz.rawOffset / 1000).toString()
        val deviceVersion = Build.VERSION.RELEASE.toString()
        val deviceModel = Build.DEVICE

        // check app ver
        val prevAppVer = SSPushPref.getPref(c, SSPushPref.PREF_APPVERSION)
        if (!prevAppVer.equals(appVersion, ignoreCase = true))
            SSPushPref.setPrefBool(c, SSPushPref.PREF_DIRTY, true)

        SSPushPref.setPref(c, SSPushPref.PREF_DEVICEUID, deviceId)
        SSPushPref.setPref(c, SSPushPref.PREF_APPVERSION, appVersion)
        SSPushPref.setPref(c, SSPushPref.PREF_DEVICENAME, deviceId)
        SSPushPref.setPref(c, SSPushPref.PREF_DEVICEMODEL, deviceModel)
        SSPushPref.setPref(c, SSPushPref.PREF_DEVICEVERSION, deviceVersion)
        SSPushPref.setPref(c, SSPushPref.PREF_LANGUGAGE, language)
        SSPushPref.setPref(c, SSPushPref.PREF_TIMEZONE, timeZone)
        SSPushPref.setPref(c, SSPushPref.PREF_COUNTRY, country)

        SSPushPref.setPref(c, SSPushPref.PREF_APPID, cmsId)
    }

    /***
     * Deprecated.. Use initializeSSPush()
     * @param c
     * @param senderID
     * @param cmsId
     */
    @Deprecated("")
    fun initPush(c: Context, senderID: String, cmsId: String) {
        val lowerCaseCMSID = cmsId.toLowerCase(Locale.US)

        var conf: SSPushConfig? = null
        if (lowerCaseCMSID.endsWith("amazon")) {
            conf = SSPushConfig.createDefaultConfig_ADM(c, cmsId)
        } else if (lowerCaseCMSID.endsWith("xiaomi")) {
            throw IllegalStateException("initPush is not supported. Use initializePush()")
        } else {
            conf = SSPushConfig.createDefaultConfig_GCM(c, cmsId)
        }
        initializeSSPush(conf!!)
    }

    fun initializeSSPush(conf: SSPushConfig) {
        val c = conf.applicationContext
        SSSharedAppContext.initialize(c)
        SSActivityLifeCycleObserver.initialize(c as Application)
        mShowPushOnForeground = conf.showPushOnForeground


        var pushClientClassName: String? = null
        when (conf.pushProvider) {
            None -> pushClientClassName = SSPUSHCLIENT_CLASSNAME_NONE
            GoogleGCM -> pushClientClassName = SSPUSHCLIENT_CLASSNAME_GCM
            AmazonADM -> pushClientClassName = SSPUSHCLIENT_CLASSNAME_ADM
            Baidu -> pushClientClassName = SSPUSHCLIENT_CLASSNAME_BAIDU
            XiaomiMiPush -> pushClientClassName = SSPUSHCLIENT_CLASSNAME_MIPUSH
            else -> throw IllegalStateException("New Push Provider? implement this..")
        }

        if (conf.pushProvider === PushServiceProvider.Baidu && (DEFAULT_SENDER_ID == conf.baiduApiKey || TextUtils.isEmpty(conf.baiduApiKey)))
            throw IllegalStateException("Baidu Push service has no default senderID")

        if (conf.pushProvider === PushServiceProvider.XiaomiMiPush && (DEFAULT_SENDER_ID == conf.mipushAppId || TextUtils.isEmpty(conf.mipushAppId) ||
                        DEFAULT_SENDER_ID == conf.mipushAppKey || TextUtils.isEmpty(conf.mipushAppKey)))
            throw IllegalStateException("Xiaomi MiPush service has no default senderID")

        SSPush.initBaseInfoPrefs(c, conf.cmsID)
        conf.applyToSSPushPref(c)

        refreshSystemTopics(c)
        SSAppProperty.addSSAppPropertyOnChangeListener(sAppPropertyOnChangeListener)


        try {
            val instance = SSPush.createSSPushClient(pushClientClassName)
            instance!!.initialize_sspush(c.getApplicationContext(), conf)
        } catch (e: Exception) {
            throw IllegalStateException(e)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Pinkfong", NotificationManager.IMPORTANCE_DEFAULT)
            //notificationChannel.setDescription("channel description");
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationManager.createNotificationChannel(notificationChannel)
        }

    }


    private fun refreshSystemTopics(c: Context) {
        val systemTopics = getTopics(c, true)
        if (SSAppProperty.isPaidUser(c))
            systemTopics.add(PREDEFINED_SYSTEM_TOPIC_NAME__PAID)
        else
            systemTopics.remove(PREDEFINED_SYSTEM_TOPIC_NAME__PAID)

        setTopics(c, systemTopics, true)
    }


    fun setBadgeEnabled(c: Context, b: Boolean) {
        SSPushPref.setPrefBool(c, SSPushPref.PREF_BADGE_ENABLED, b)
    }

    fun enableBigView(c: Context, b: Boolean) {
        SSPushPref.setPrefBool(c, SSPushPref.PREF_NOTICENTER_BIGVIEW, b)
    }

    fun resetBadgeCount(c: Context) {
        val clsName = getIntentClass(c)
        if (clsName.length > 0) {
            setBadgeCount(c, 0, c.packageName, clsName)
        }
    }

    fun setBadgeCount(c: Context, badgeCnt: Int) {
        if (false == SSPushPref.getPrefBool(c, SSPushPref.PREF_BADGE_ENABLED, false))
            return

        val pkgName = c.packageName
        val clsName = getIntentClass(c)

        setBadgeCount(c, badgeCnt, pkgName, clsName)
    }

    private fun setBadgeCount(c: Context, badgeCnt: Int, pkgName: String, clsName: String) {

        try {
            val badgeIntent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
            badgeIntent.putExtra("badge_count", badgeCnt)
            badgeIntent.putExtra("badge_count_package_name", pkgName)
            badgeIntent.putExtra("badge_count_class_name", clsName)
            c.sendBroadcast(badgeIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun getPushNoti(context: Context): Boolean {
        return SSPushPref.getPrefBool(context, SSPushPref.PREF_PUSH_ONOFF, true)
    }

    fun setPushNoti(context: Context, b: Boolean) {
        SSPushPref.setPrefBool(context, SSPushPref.PREF_PUSH_ONOFF, b)
    }

    fun getPausePushNoti(context: Context): Boolean {
        return SSPushPref.getPrefBool(context, SSPushPref.PREF_PAUSE_PUSH, false)
    }

    fun setPausePushNoti(context: Context, b: Boolean) {
        SSPushPref.setPrefBool(context, SSPushPref.PREF_PAUSE_PUSH, b)
    }

    /**
     * PlayerKey 및 Topics 에 대해서 설정한다. setPlayerPublicKey 및 addTopics , removeTopics를 쓰시면 좋지요..
     * @param context
     * @param playerPublicKey
     * @param sspushTopics
     */
    @Deprecated("")
    fun setPlayerPublicKeyAndTopics(context: Context, playerPublicKey: String, sspushTopics: String) {
        val c = context.applicationContext

        setPlayerPublicKey(c, playerPublicKey)
        val topics = if (TextUtils.isEmpty(sspushTopics) == false) Arrays.asList(*TextUtils.split(sspushTopics, ",")) else null

        setTopics(context, topics)
    }

    fun getPlayerPublicKey(c: Context): String {
        return SSPushPref.getPref(c, SSPushPref.PREF_PLAYER_PUBLICKEY)
    }

    fun setPlayerPublicKey(c: Context, playerPublicKey: String) {
        if (SSPushPref.setPref(c, SSPushPref.PREF_PLAYER_PUBLICKEY, playerPublicKey)) {
            registerToSSPushServer(c, true)
        }

    }

    internal fun createSSPushClient(className: String): ISSPushClient? {
        var ret: ISSPushClient? = null
        try {
            if (TextUtils.isEmpty(className))
                return null
            val clasz = Class.forName(className)
            val cons = clasz.getConstructor()
            val `object` = cons.newInstance()
            if (`object` is ISSPushClient)
                ret = `object` as ISSPushClient

        } catch (e: Exception) {
            Log.e(TAG, "", e)
        }

        return ret
    }


    /**
     * App 토픽을 설정합니다. 기존 토픽들은 지워집니다.
     * @param context
     * @param topics
     */
    fun setTopics(context: Context, topics: Collection<String>?) {
        setTopics(context, topics, false)
    }

    /**
     * 토픽을 설정합니다. 기존 토픽들은 지워집니다.
     * @param context
     * @param topics
     * @param systemTopic
     * false : App토픽
     * true : System토픽
     */
    private fun setTopics(context: Context, topics: Collection<String>?, systemTopic: Boolean) {
        var topics = topics
        val list = TreeSet<String>()
        if (topics == null)
            topics = ArrayList()
        for (topic in topics) {
            topic = topic?.trim { it <= ' ' } ?: ""
            if (TextUtils.isEmpty(topic) == false)
                list.add(topic)
        }
        val listString = TextUtils.join(",", list)
        SSLog.i(TAG, "setTopics : $listString")
        val invalidated = SSPushPref.setPref(
                context,
                if (systemTopic) SSPushPref.PREF_SSPUSH_TOPICS_FOR_SYSTEM else SSPushPref.PREF_SSPUSH_TOPICS_FOR_APP,
                listString)
        if (invalidated)
            registerToSSPushServer(context, true)
    }

    /**
     * App에서 설정한 토픽을 가져옵니다.
     * @param context
     * @return
     */
    fun getTopics(context: Context): Set<String> {
        return getTopics(context, false)
    }

    /**
     * 토픽을 가져옵니다.
     * @param context
     * @param systemTopic
     * false : App토픽
     * true : System토픽
     * @return
     */
    private fun getTopics(context: Context, systemTopic: Boolean): MutableSet<String> {
        val list = TreeSet<String>()
        val listString = SSPushPref.getPref(context,
                if (systemTopic) SSPushPref.PREF_SSPUSH_TOPICS_FOR_SYSTEM else SSPushPref.PREF_SSPUSH_TOPICS_FOR_APP)
        val topics = TextUtils.split(listString, ",")
        for (topic in topics) {
            topic = topic?.trim { it <= ' ' } ?: ""
            if (TextUtils.isEmpty(topic) == false)
                list.add(topic)
        }
        return list
    }

    /**
     * App,System 토픽들을 모두 가져옵니다.
     * @param context
     * @return
     */
    fun getTopicsAll(context: Context): Set<String> {
        val app = getTopics(context, false)
        val system = getTopics(context, true)
        app.addAll(system)
        return app
    }

    private fun registerToSSPushServer(c: Context, force: Boolean) {
        if (force) {
            SSPushPref.setPrefBool(c, SSPushPref.PREF_DIRTY, true)
        }

        val client = SSPush.createSSPushClient(getPushClientClassName(c))
        if (client != null) {
            client!!.registerToSSPushServer(c)
        }
    }

    /**
     * Activity의 onCreate() , onNewIntent() 에서 호출해줍니다. 푸시에 의해 실행된 것인지 확인합니다. 한번 실행된 Noti의 정보는 가지고 있기때문에 여러번 해도 상관없습니다.
     * @param activity
     */
    fun logPushNotificationOpen(activity: Activity?, intent: Intent?) {
        var intent = intent
        if (activity == null)
            return
        if (intent == null)
            intent = activity.intent

        if (intent == null)
            return

        val push_noti_time = intent.getLongExtra(SSPushMsgHandler.EXTRA_KEY_SSPUSH_NOTI_TIME, -1)
        val last_logged_noti_time = SSPushPref.getPrefLong(activity, SSPushPref.PREF_SSPUSH_LAST_LOGGED_NOTI_TIME, 0)
        if (last_logged_noti_time != push_noti_time && push_noti_time > 0) {
            SSLog.d(TAG, "logPushNotificationOpen()")
            SSPushPref.setPrefLong(activity, SSPushPref.PREF_SSPUSH_LAST_LOGGED_NOTI_TIME, push_noti_time)
            val broadcastIntent = Intent(ACTION_BROADCAST_LOG_PUSH_OPEN)
            if (intent.extras != null) {
                broadcastIntent.putExtras(intent.extras!!)
                LocalBroadcastManager.getInstance(activity.applicationContext).sendBroadcast(broadcastIntent)
                // 나중에 우리 로그로도 쏴보자..
            }
        }

    }
    */

}