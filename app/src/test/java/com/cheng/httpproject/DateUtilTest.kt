package com.cheng.httpproject

import com.cheng.httpproject.util.DateUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class DateUtilTest {

    val calendar = Calendar.getInstance()

    @Test
    fun testFormatDate() {
        calendar.set(2018, Calendar.JANUARY, 1)
        val newYear2018 = calendar.time
        val newYearString = DateUtil.formatDate(newYear2018)
        val expectedString = "2018-01-01"

        assertEquals(expectedString, newYearString)
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
