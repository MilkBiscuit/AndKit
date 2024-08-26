package com.cheng.andkit.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.abs


object DateUtil {

    const val SECOND_IN_MILLIS: Long = 1000
    const val MINUTE_IN_MILLIS: Long = SECOND_IN_MILLIS * 60
    const val HOUR_IN_MILLIS: Long = MINUTE_IN_MILLIS * 60
    const val DAY_IN_MILLIS: Long = HOUR_IN_MILLIS * 24
    const val WEEK_IN_MILLIS: Long = DAY_IN_MILLIS * 7

    private const val TAG = "DateUtil";
    private const val FORMAT_YEAR_MONTH_DATE = "yyyy-MM-dd";
    private const val FORMAT_Y_M_D_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private const val WEEKDAY = "EEEE"
    private const val TIMEZONE = "XXX" // +13:00

    fun parseYearMonthDate(date: String) : Date? {
        return parseDate(date, FORMAT_YEAR_MONTH_DATE)
    }

    fun parseFullDateTime(date: String) : Date? {
        return parseDate(date, FORMAT_Y_M_D_DATE_TIME)
    }

    fun parseDate(date: String, dateFormat: String): Date? {
        return try {
            val formatter = SimpleDateFormat(dateFormat, Locale.getDefault());

            formatter.parse(date)
        } catch (e: Exception) {
            Log.w(TAG, "parseDate exception: ${e.message}")

            null
        }
    }

    fun formatDate(date: Date, dateFormat: String = FORMAT_YEAR_MONTH_DATE): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault());

        return formatter.format(date)
    }

    fun getTimeZoneOffset(): String {
        val now = Date()
        return formatDate(date = now, dateFormat = TIMEZONE)
    }

    fun getWeekday(date: Date): String {
        val formatter = SimpleDateFormat(WEEKDAY, Locale.getDefault());

        return formatter.format(date)
    }

    fun getToday(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time
    }

    fun getTomorrow(): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = getToday().time + DAY_IN_MILLIS

        return calendar.time
    }

    fun isSameDay(date: Date, compareDate: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val compareCalendar = Calendar.getInstance()
        compareCalendar.time = compareDate

        return calendar.get(Calendar.YEAR) == compareCalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == compareCalendar.get(Calendar.MONTH)
                && calendar.get(Calendar.DATE) == compareCalendar.get(Calendar.DATE)
    }

}
