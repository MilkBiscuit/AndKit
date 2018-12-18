package com.cheng.httpproject

import com.cheng.httpproject.util.StringUtil
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StringUtilTest {

    companion object {
        @JvmStatic
        fun colourValues() = listOf(
                Arguments.of("#FFF", true),
                Arguments.of("#000", true),
                Arguments.of("#abc", true),
                Arguments.of("#778899", true),
                Arguments.of("7F7F7F", false),
                Arguments.of("#0000", false),
                Arguments.of("#UVWXYZ", false),
                Arguments.of("#Apple", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        )
    }

    @Test
    fun testParseUrlFromString() {
        val url = "http://www.google.com"
        val testString = "Please visit ${url} to book our latest events"

        val resultList = StringUtil.getUrlsFromString(testString)
        Assert.assertTrue(!resultList.isEmpty())
        assertEquals(url, resultList[0])
    }

    @ParameterizedTest
    @MethodSource("colourValues")
    fun testSquares(input: String?, expected: Boolean) {
        val result = StringUtil.isValidColourHex(input)
        assertEquals(expected, result)
    }

}
