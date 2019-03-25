package com.passionvirus.pvutils

import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.NonNull
import android.support.annotation.Nullable

class AppPreferences {

    companion object {
        private lateinit var prefs : SharedPreferences
        private var prefName : String = "APP_PREF_DEFAULT"
        private var prefMode : Int = Context.MODE_PRIVATE"
    }

    constructor(@NonNull context : Context) {
        AppPreferences(context, prefName, prefMode)
    }

    constructor(@NonNull c : Context, @NonNull prefName : String, @NonNull prefMode : Int) {
        context = c
        prefs = context.getSharedPreferences(prefName, prefMode)
    }

    lateinit var context : Context

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

    fun getPrefBoolean(k : String, @Nullable default : Boolean = false) : Boolean {
        return prefs.getBoolean(k, default)
    }

    fun setPrefBoolean(k : String, v : Boolean) {
        prefs.edit().putBoolean(k, v).apply()
    }

    fun getPrefInt(k : String, @Nullable default : Int = 0) : Int {
        return prefs.getInt(k, default)
    }

    fun setPrefInt(k : String, v : Int) {
        prefs.edit().putInt(k, v).apply()
    }

    fun getPrefFloat(k : String, @Nullable default : Float = 0F) : Float {
        return prefs.getFloat(k, default)
    }

    fun setPrefFloat(k : String, v : Float) {
        prefs.edit().putFloat(k, v).apply()
    }

    fun getPrefLong(k : String, @Nullable default : Long = 0) : Long {
        return prefs.getLong(k, default)
    }

    fun setPrefLong(k : String, v : Long) {
        prefs.edit().putLong(k, v).apply()
    }

    fun getPrefString(k : String, @Nullable default : String = "") : String {
        return prefs.getString(k, default) ?: default
    }

    fun setPrefString(k : String, v : String) {
        prefs.edit().putString(k, v).apply()
    }
}