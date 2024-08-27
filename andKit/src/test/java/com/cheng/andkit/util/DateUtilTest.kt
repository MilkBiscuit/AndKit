package com.cheng.andkit.util

import log.LumberjackRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.ClassRule
import org.junit.Test
import java.util.Calendar
import java.util.Date


class DateUtilTest {

    private val calendar = Calendar.getInstance()

    @Test
    fun `getTimeZoneOffset - returns correct string`() {
        val result = DateUtil.getTimeZoneOffset()

        // Given the test is run in NZ
        assertTrue(result == "+12:00" || result == "+13:00")
        // The test is not necessarily run in NZ, in that case, check the string format instead
        assertEquals(6, result.length)
        assertTrue(result[0] == '+' || result[0] == '-')
        assertTrue(result[1] == '0' || result[1] == '1')
        assertTrue(result[5] == '0')
    }

    @Test
    fun `parseDate - returns correct value`() {
        val febFirst = "2018-02-01"
        val result = DateUtil.parseYearMonthDate(febFirst)
        calendar.set(2018, Calendar.FEBRUARY, 1, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val expected = calendar.time

        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))
    }

    @Test
    fun `parseDate - Given string is invalid, Then no exception is thrown`() {
        var invalidDate = ""
        var result = DateUtil.parseYearMonthDate(invalidDate)
        assertNull(result)

        invalidDate = "aabb-cc-dd"
        result = DateUtil.parseYearMonthDate(invalidDate)
        assertNull(result)
    }

    @Test
    fun `parseFullDateTime - returns correct value`() {
        var feb14 = "2020-02-14 02:14:00"
        var result = DateUtil.parseFullDateTime(feb14)
        calendar.set(2020, Calendar.FEBRUARY, 14, 2, 14, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        var expected = calendar.time
        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))

        feb14 = "1969-02-14 00:00:00"
        result = DateUtil.parseFullDateTime(feb14)
        calendar.set(1969, Calendar.FEBRUARY, 14, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        expected = calendar.time
        assertEquals(expected, result)
        assertEquals(0, expected.compareTo(result))
    }

    @Test
    fun `parseFullDateTime - Given string is invalid, Then no exception is thrown`() {
        var invalidDate = ""
        var result = DateUtil.parseFullDateTime(invalidDate)
        assertNull(result)

        invalidDate = "aabb-cc-dd"
        result = DateUtil.parseFullDateTime(invalidDate)
        assertNull(result)
    }

    @Test
    fun `formatDate - returns correct string`() {
        calendar.set(2018, Calendar.JANUARY, 1)
        var input = calendar.time
        var result = DateUtil.formatDate(input, "yyyy-MM-dd")
        var expectedString = "2018-01-01"
        assertEquals(expectedString, result)

        calendar.set(1970, Calendar.JANUARY, 1)
        input = calendar.time
        result = DateUtil.formatDate(input, "yyyy-MM-dd")
        expectedString = "1970-01-01"
        assertEquals(expectedString, result)
    }

    @Test
    fun `getToday - returns correct value`() {
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
    fun `isSameDay - Given 2 date objects in the same day, Then returns true`() {
        // 2018-1-1, 00:00:00 and 00:00:01
        calendar.set(2018, Calendar.JANUARY, 1, 0, 0, 0)
        var input1 = calendar.time
        calendar.set(2018, Calendar.JANUARY, 1, 0, 0, 1)
        var input2 = calendar.time
        var result = DateUtil.isSameDay(input1, input2)
        assertTrue(result)

        // 2024-8-26, 00:00:00 and 23:59:59
        calendar.set(2024, Calendar.AUGUST, 26, 0, 0, 0)
        input1 = calendar.time
        calendar.set(2024, Calendar.AUGUST, 26, 23, 59, 59)
        input2 = calendar.time
        result = DateUtil.isSameDay(input1, input2)
        assertTrue(result)
    }

    @Test
    fun `isSameDay - Given 2 date objects NOT in the same day, Then returns false`() {
        val today = DateUtil.getToday()
        val tomorrow = DateUtil.getTomorrow()
        var result = DateUtil.isSameDay(today, tomorrow)
        assertFalse(result)

        // 2018-1-1 23:59:59 and 2018-1-2 00:00:00
        calendar.set(2018, Calendar.JANUARY, 2, 0, 0, 0)
        val input1 = calendar.time
        calendar.set(2018, Calendar.JANUARY, 1, 23, 59, 59)
        val input2 = calendar.time
        result = DateUtil.isSameDay(input1, input2)
        assertFalse(result)
    }

    companion object {
        @get:ClassRule
        @JvmStatic
        var lumberjackRule = LumberjackRule()
    }

}
