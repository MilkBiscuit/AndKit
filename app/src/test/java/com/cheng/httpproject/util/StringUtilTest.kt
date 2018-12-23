package com.cheng.httpproject.util

import com.cheng.httpproject.util.StringUtil
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StringUtilTest {

    companion object {
        @JvmStatic
        fun capitaliseValues() = listOf(
                Arguments.of("apple", "Apple"),
                Arguments.of("One apple a day", "One apple a day"),
                Arguments.of("OMG", "OMG"),
                Arguments.of("134#$%&", "134#$%&"),
                Arguments.of("≠ banana", "≠ banana"),
                Arguments.of("aPPle", "APPle"),
                Arguments.of("中文字符", "中文字符"),
                Arguments.of("   ", "   "),
                Arguments.of("", ""),
                Arguments.of(null, null)
        )

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
        var testString = "Please visit ${url} to book our latest events"

        var resultList = StringUtil.getUrlsFromString(testString)
        Assert.assertTrue(!resultList.isEmpty())
        assertEquals(url, resultList[0])


        val url2 = "http://www.baidu.com"
        testString = "#$url2 sucks!, please use $url instead $$## "

        resultList = StringUtil.getUrlsFromString(testString)
        Assert.assertTrue(!resultList.isEmpty())
        assertEquals(url2, resultList[0])
        assertEquals(url, resultList[1])
    }

    @ParameterizedTest
    @MethodSource("capitaliseValues")
    fun testCaptalise(input: String?, expected: String?) {
        val result = StringUtil.capitaliseFirstLetter(input)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("colourValues")
    fun testIsValidColourHex(input: String?, expected: Boolean) {
        val result = StringUtil.isValidColourHex(input)
        assertEquals(expected, result)
    }

}
