package com.cheng.httpproject.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    companion object {

        private const val TAG = "DateUtil";
        private const val MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000L
        private const val FORMAT_YEAR_MONTH_DATE = "yyyy-MM-dd";
        private const val FORMAT_Y_M_D_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

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
                parsed = formatter.parse(date)
            } catch (e: Exception) {
                Log.w(TAG, e.message)
            }

            return parsed
        }

        fun formatDate(date: Date, dateFormat: String = FORMAT_YEAR_MONTH_DATE): String {
            val formatter = SimpleDateFormat(dateFormat, Locale.getDefault());

            return formatter.format(date)
        }

        fun getToday(): Date {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            return  calendar.time
        }

        fun getTomorrow(): Date {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = getToday().time + MILLISECONDS_PER_DAY

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
}