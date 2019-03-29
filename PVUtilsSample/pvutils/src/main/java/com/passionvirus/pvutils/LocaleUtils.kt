package com.passionvirus.pvutils

import android.content.Context
import android.os.Build


class LocaleUtils {
    companion object {
        private var context: Context? = null
        const val LOCAL_COOUNTRY_CODE = "LOCAL_COOUNTRY_CODE"

        fun setContext(c : Context) {
            context = c
        }

        fun getCoutryCode() : String {
            return context?.let {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.resources.configuration.locales.get(0).country
                } else {
                    it.resources.configuration.locale.country
                }
            } ?: ""
        }

        fun getLocalCountryCode() : String {
            return context?.let {
                AppPreferences(it).getPrefString(LOCAL_COOUNTRY_CODE, getCoutryCode())
            } ?: getCoutryCode()
        }

        fun setLocalCountryCode(country : String) {
            context?.let {
                AppPreferences(it).setPrefString(LOCAL_COOUNTRY_CODE, country)
            }
        }

        fun getLanguage() : String {
            return context?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    it.resources.configuration.locales.get(0).language
                } else {
                    it.resources.configuration.locale.language
                }
            } ?: ""
        }
    }
}