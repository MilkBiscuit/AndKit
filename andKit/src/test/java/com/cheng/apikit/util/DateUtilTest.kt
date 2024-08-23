package com.cheng.apikit.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.robolectric.annotation.Config
import java.util.Calendar
import java.util.Date

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
        var invalidDate = ""
        DateUtil.parseYearMonthDate(invalidDate)
        invalidDate = "aabb-cc-dd"
        DateUtil.parseYearMonthDate(invalidDate)
    }

    @Test
    fun testParseFullDateTime() {
        // 2020-Feb-14 02:14:00
        calendar.set(2020, Calendar.FEBRUARY, 14, 2, 14, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        var expected = calendar.time
        var feb14 = "2020-02-14 02:14:00"
        var result = DateUtil.parseFullDateTime(feb14)
        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))

        // 1969-Feb-14 00:00:00
        feb14 = "1969-02-14 00:00:00"
        result = DateUtil.parseFullDateTime(feb14)
        calendar.set(1969, Calendar.FEBRUARY, 14, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        expected = calendar.time
        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))

        // Exception was handled without crash
        var invalidDate = ""
        DateUtil.parseFullDateTime(invalidDate)
        invalidDate = "aabb-cc-dd"
        DateUtil.parseFullDateTime(invalidDate)
    }

    @Test
    fun testFormatDate() {
        // 2018-1-1
        calendar.set(2018, Calendar.JANUARY, 1)
        var input = calendar.time
        var result = DateUtil.formatDate(input, "yyyy-MM-dd")
        var expectedString = "2018-01-01"

        // 1970-1-1
        calendar.set(1970, Calendar.JANUARY, 1)
        input = calendar.time
        result = DateUtil.formatDate(input, "yyyy-MM-dd")
        expectedString = "1970-01-01"

        assertEquals(expectedString, result)
    }

    @Test
    fun testToday() {
        val now = Date().time
        val today = DateUtil.getToday()
        val tomorrow = DateUtil.getTomorrow()

        calendar.time = today
        assertTrue(now >= today.time && now < tomorrow.time)
        assertEquals(0, calendar.get(Calendar.HOUR_OF_DAY))
        assertEquals(0, calendar.get(Calendar.MINUTE))
        assertEquals(0, calendar.get(Calendar.SECOND))
        assertEquals(0, calendar.get(Calendar.MILLISECOND))
    }

    @Test
    fun testIsSameDay() {
        val today = DateUtil.getToday()
        val tomorrow = DateUtil.getTomorrow()
        var result = DateUtil.isSameDay(today, tomorrow)
        assertFalse(result)

        // 2018-1-1, 00:00:00 and 00:00:01
        calendar.set(2018, Calendar.JANUARY, 1, 0, 0, 0)
        var input1 = calendar.time
        calendar.set(2018, Calendar.JANUARY, 1, 0, 0, 1)
        var input2 = calendar.time
        result = DateUtil.isSameDay(input1, input2)
        assertTrue(result)

        // 2018-1-1, 00:00:00 and 23:59:59
        calendar.set(2018, Calendar.JANUARY, 1, 0, 0, 0)
        input1 = calendar.time
        calendar.set(2018, Calendar.JANUARY, 1, 23, 59, 59)
        input2 = calendar.time
        result = DateUtil.isSameDay(input1, input2)
        assertTrue(result)

        // 2018-1-1 23:59:59 and 2018-1-2 00:00:00
        calendar.set(2018, Calendar.JANUARY, 2, 0, 0, 0)
        input1 = calendar.time
        calendar.set(2018, Calendar.JANUARY, 1, 23, 59, 59)
        input2 = calendar.time
        result = DateUtil.isSameDay(input1, input2)
        assertFalse(result)
    }

}
