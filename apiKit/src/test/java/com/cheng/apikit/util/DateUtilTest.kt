package com.cheng.apikit.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class DateUtilTest {

    private val calendar = Calendar.getInstance()

    @Test
    fun testGetTimeZoneOffset() {
        val result = DateUtil.getTimeZoneOffset()

        // e.g. +12:00, -06:00
        assertEquals(6, result.length)
        assertTrue(result[0] == '+' || result[0] == '-')
        assertTrue(result[1] == '0' || result[1] == '1')
        assertTrue(result[4] == '0')
        assertTrue(result[5] == '0')
    }

    @Test
    fun testParseDate() {
        calendar.set(2018, Calendar.FEBRUARY, 1, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val expected = calendar.time
        val febFirst = "2018-02-01"
        val result = DateUtil.parseYearMonthDate(febFirst)

        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))

        // Exception was handled without crash
        val invalidDate = ""
        DateUtil.parseYearMonthDate(invalidDate)
    }

    @Test
    fun testParseFullDateTime() {
        // 2020-Feb-14 02:14:00
        calendar.set(2020, Calendar.FEBRUARY, 14, 2, 14, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val expected = calendar.time
        val feb14 = "2020-02-14 02:14:00"
        val result = DateUtil.parseFullDateTime(feb14)

        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))
    }

    @Test
    fun testFormatDate() {
        calendar.set(2018, Calendar.JANUARY, 1)
        val newYear2018 = calendar.time
        val newYearString = DateUtil.formatDate(newYear2018)
        val expectedString = "2018-01-01"

        assertEquals(expectedString, newYearString)
    }

    @Test
    fun testTodayOrTomorrow() {
        val now = Date().time
        val today = DateUtil.getToday()
        val tomorrow = DateUtil.getTomorrow()

        assertTrue(now >= today.time && now < tomorrow.time)
        assertTrue(today.before(tomorrow))
    }

}
