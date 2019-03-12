package com.passionvirus.pvutils

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.NonNull
import androidx.annotation.Nullable

class AppPreferences {
    companion object {
        private lateinit var mContext: Context
        private lateinit var mPrefs: SharedPreferences
        private var mPrefName: String = "APP_PREF_DEFAULT"
        private var mPrefMode: Int = Context.MODE_PRIVATE
    }

    constructor(@NonNull context: Context) {
        AppPreferences(context, mPrefName, mPrefMode)
    }

    constructor(@NonNull context: Context, @NonNull prefName: String, @NonNull prefMode: Int) {
        mContext = context
        mPrefs = mContext.getSharedPreferences(mPrefName, mPrefMode)
    }

    /*
    fun getPrefName(): String {
        return mPrefName
    }

    fun setPrefName(name: String) {
        mPrefName = name
    }

    fun getPrefMode(): Int {
        return mPrefMode
    }

    fun setPrefMode(mode: Int) {
        mPrefMode = mode
    }

    fun getPref() {
        context.getSharedPreferences(mPrefName, mPrefMode)
    }
    */

    fun getPrefBoolean(k: String, @Nullable default:Boolean = false): Boolean {
        return mPrefs.getBoolean(k, default)
    }

    fun setPrefBoolean(k: String, v: Boolean) {
        mPrefs.edit().putBoolean(k, v).apply()
    }

    fun getPrefInt(k: String, @Nullable default:Int = 0): Int {
        return mPrefs.getInt(k, default)
    }

    fun setPrefInt(k: String, v: Int) {
        mPrefs.edit().putInt(k, v).apply()
    }

    fun getPrefFloat(k: String, @Nullable default:Float = 0F): Float {
        return mPrefs.getFloat(k, default)
    }

    fun setPrefFloat(k: String, v: Float) {
        mPrefs.edit().putFloat(k, v).apply()
    }

    fun getPrefLong(k: String, @Nullable default:Long = 0): Long {
        return mPrefs.getLong(k, default)
    }

    fun setPrefLong(k: String, v: Long) {
        mPrefs.edit().putLong(k, v).apply()
    }

    fun getPrefString(k: String, @Nullable default:String = ""): String {
        return mPrefs.getString(k, default)
    }

    fun setPrefString(k: String, v: String) {
        mPrefs.edit().putString(k, v).apply()
    }
}