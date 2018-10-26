package com.passionvirus.pvutils

import android.content.Context
import android.content.SharedPreferences
import android.support.annotation.NonNull

class AppPreferences {
    companion object {
        private var mPrefName: String = "APP_PREF_DEFAULT"
        private var mPrefs: SharedPreferences? = null
        private var mPrefMode: Int = Context.MODE_PRIVATE
//        private var mPrefs: SharedPreferences? = null
    }

    constructor(@NonNull context: Context) {
        AppPreferences(context, mPrefName, mPrefMode)
    }

    constructor(@NonNull context: Context, @NonNull prefName: String, @NonNull prefMode: Int) {
        mPrefs = context.getSharedPreferences(mPrefName, mPrefMode)
    }

    fun setPrefName(name: String) {
        mPrefName = name
    }

    fun getPrefName(): String {
        return mPrefName
    }

    fun setPrefMode(mode: Int) {
        mPrefMode = mode
    }

    fun getPrefMode(): Int {
        return mPrefMode
    }
}