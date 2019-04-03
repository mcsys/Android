package com.passionvirus.pvutils

import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    companion object {
        private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val DEFAULT_TIMEZONE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        private const val DEFAULT_TIMEZONE = "GMT"

        lateinit var dateFormat: String
        lateinit var dateLocale: Locale
        lateinit var dateTimezone: TimeZone
        val calendar = Calendar.getInstance()

        fun getDateTimeInMillis(): Long {
            return Calendar.getInstance().timeInMillis
        }

        fun getCustomDateTimeInMillis(): Long {
            return calendar.timeInMillis
        }

        fun setCalendarYear(year: Int) {
            calendar.set(Calendar.YEAR, year)
        }

        fun addCalendarYear(year: Int) {
            calendar.add(Calendar.YEAR, year)
        }

        fun setCalendarMonth(month: Int) {
            calendar.set(Calendar.MONTH, month)
        }

        fun addCalendarMonth(month: Int) {
            calendar.add(Calendar.MONTH, month)
        }

        fun setCalendarDay(day: Int) {
            calendar.set(Calendar.DAY_OF_YEAR, day)
        }

        fun addCalendarDay(day: Int) {
            calendar.add(Calendar.DAY_OF_YEAR, day)
        }

        fun setCalendarHour(hour: Int) {
            calendar.set(Calendar.HOUR_OF_DAY, hour)
        }

        fun addCalendarHour(hour: Int) {
            calendar.add(Calendar.HOUR_OF_DAY, hour)
        }

        fun setCalendarMinute(minute: Int) {
            calendar.set(Calendar.MINUTE, minute)
        }

        fun addCalendarMinute(minute: Int) {
            calendar.add(Calendar.MINUTE, minute)
        }

        fun setCalendarSecond(second: Int) {
            calendar.set(Calendar.SECOND, second)
        }

        fun addCalendarSecond(second: Int) {
            calendar.add(Calendar.SECOND, second)
        }

        fun getDate(timeInMillis: Long): Date {
            return Date(timeInMillis)
        }

        fun getCustomDate(): Date {
            return Date(calendar.timeInMillis)
        }

        fun getDateTime(): String {
            if (!::dateFormat.isInitialized) {
                dateFormat = DEFAULT_DATE_FORMAT
            }

            if (!::dateLocale.isInitialized) {
                dateLocale = Locale.getDefault()
            }

            return getDateTime(dateFormat, dateLocale)
        }

        fun getDateTime(format: String, locale: Locale): String {
            val calendar = Calendar.getInstance()
            val date = calendar.time

            return SimpleDateFormat(format, locale).format(date)
        }

        fun getDateTimeWithTimeZone(): String {
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

        fun getDateTimeWithTimeZone(format: String, locale: Locale, timezone: TimeZone): String {
            val calendar = Calendar.getInstance()
            val date = calendar.time

            val sdf = SimpleDateFormat(format, locale)
            sdf.timeZone = timezone
            sdf.format(date)

            return sdf.format(date)
        }

        fun isBefore(date1: Date, date2: Date): Boolean {
            return date1.before(date2)
        }

        fun isBefore(timeInMillis1: Long, timeInMillis2: Long): Boolean {
            val date1 = getDate(timeInMillis1)
            val date2 = getDate(timeInMillis2)
            return date1.before(date2)
        }

    }
}