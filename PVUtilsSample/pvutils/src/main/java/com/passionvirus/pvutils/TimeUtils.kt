package com.passionvirus.pvutils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    companion object {
        private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val DEFAULT_TIMEZONE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val DEFAULT_TIMEZONE = "GMT"

        lateinit var dateFormat : String
        lateinit var dateLocale : Locale
        lateinit var dateTimezone : TimeZone

        fun getDateTime() : String {
            if (!::dateFormat.isInitialized) {
                dateFormat = DEFAULT_DATE_FORMAT
            }

            if (!::dateLocale.isInitialized) {
                dateLocale = Locale.getDefault()
            }

            return getDateTime(dateFormat, dateLocale)
        }

        fun getDateTime(format : String, locale : Locale) : String {
            val calendar = Calendar.getInstance()
            val date = calendar.time

            return SimpleDateFormat(format, locale).format(date)
        }

        fun getDateTimeWithTimeZone() : String {
            if (!::dateFormat.isInitialized) {
                dateFormat = DEFAULT_TIMEZONE_DATE_FORMAT
            }

            if (!::dateLocale.isInitialized) {
                dateLocale = Locale.getDefault()
            }

            if (!::dateTimezone.isInitialized) {
                dateTimezone = TimeZone.getTimeZone(DEFAULT_TIMEZONE)
            }

            return getDateTimeWithTimeZone(dateFormat, dateLocale, dateTimezone)
        }

        fun getDateTimeWithTimeZone(format : String, locale : Locale, timezone : TimeZone) : String {
            val calendar = Calendar.getInstance()
            val date = calendar.time

            val sdf = SimpleDateFormat(format, locale)
            sdf.timeZone = timezone
            sdf.format(date)

            return sdf.format(date)
        }





    }
}