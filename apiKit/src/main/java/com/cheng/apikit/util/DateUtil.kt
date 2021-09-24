package com.cheng.apikit.util

import android.text.format.DateUtils
import android.text.format.DateUtils.HOUR_IN_MILLIS
import android.text.format.DateUtils.MINUTE_IN_MILLIS
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


object DateUtil {

    private const val TAG = "DateUtil";
    private const val FORMAT_YEAR_MONTH_DATE = "yyyy-MM-dd";
    private const val FORMAT_Y_M_D_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private const val WEEKDAY = "EEEE"

    fun parseYearMonthDate(date: String) : Date {
        return parseDate(date, FORMAT_YEAR_MONTH_DATE)
    }

    fun parseFullDateTime(date: String) : Date {
        return parseDate(date, FORMAT_Y_M_D_DATE_TIME)
    }

    fun parseDate(date: String, dateFormat: String): Date {
        var parsed = Date()
        try {
            val formatter = SimpleDateFormat(dateFormat, Locale.getDefault());
            parsed = formatter.parse(date) ?: Date()
        } catch (e: Exception) {
            Log.w(TAG, "parseDate exception: ${e.message}")
        }

        return parsed
    }

    fun formatDate(date: Date, dateFormat: String = FORMAT_YEAR_MONTH_DATE): String {
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault());

        return formatter.format(date)
    }

    fun getTimeZoneOffset(): String {
        // Including daylight savings
        var offset = TimeZone.getDefault().rawOffset + Calendar.getInstance()[Calendar.DST_OFFSET]
        val sign = if (offset > 0) "+" else "-"
        offset = abs(offset)
        val hour = (offset / HOUR_IN_MILLIS).toInt()
        offset -= (hour * HOUR_IN_MILLIS).toInt()
        val minute = (offset / MINUTE_IN_MILLIS).toInt()

        return String.format("$sign%02d:%02d", hour, minute)
    }

    fun getWeekday(date: Date): String {
        val formatter = SimpleDateFormat(WEEKDAY, Locale.getDefault());

        return formatter.format(date)
    }

    fun getToday(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time
    }

    fun getTomorrow(): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = getToday().time + DateUtils.DAY_IN_MILLIS

        return  calendar.time
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
