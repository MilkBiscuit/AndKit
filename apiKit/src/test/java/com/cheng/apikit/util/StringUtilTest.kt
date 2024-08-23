package com.cheng.apikit.util

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
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
                Arguments.of("#ffffffff", true),
                Arguments.of("7F7F7F", false),
                Arguments.of("#0000", false),
                Arguments.of("#12345", false),
                Arguments.of("#UVWXYZ", false),
                Arguments.of("#Apple", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        )

        @JvmStatic
        fun base64ToBoolean() = listOf(
                Arguments.of("bGlnaHQgdw==", true),
                Arguments.of("#000", false),
                Arguments.of("\t", false),
                Arguments.of(" ", false),
                Arguments.of("", true),
                Arguments.of(null, false)
        )

        @JvmStatic
        fun base64ToText() = listOf(
                Arguments.of("bGlnaHQgdw==", "light w"),
                Arguments.of("bGlnaHQgd28=", "light wo"),
                Arguments.of("bGlnaHQgd29y", "light wor")
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
        testString = "#$url2 is not recommended, please use $url instead $$## "

        resultList = StringUtil.getUrlsFromString(testString)
        Assert.assertTrue(!resultList.isEmpty())
        assertEquals(url2, resultList[0])
        assertEquals(url, resultList[1])
    }

    @ParameterizedTest
    @MethodSource("capitaliseValues")
    fun testCapitalise(input: String?, expected: String?) {
        val result = StringUtil.capitaliseFirstLetter(input)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("colourValues")
    fun testIsValidColourHex(input: String?, expected: Boolean) {
        val result = StringUtil.isValidColourHex(input)
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("base64ToBoolean")
    fun testIsBase64Format(input: String?, expected: Boolean) {
        val result = StringUtil.isBase64Format(input)
        assertEquals(expected, result)
    }

    @Test
    fun testDecodeBase64() {
        var result = StringUtil.decodeBase64("bGlnaHQgdw==")
        var expected = "light w"
        assertEquals(expected, result)
        result = StringUtil.decodeBase64("bGlnaHQgd28")
        expected = "light wo"
        assertEquals(expected, result)
        result = StringUtil.decodeBase64("bGlnaHQgd29y")
        expected = "light wor"
        assertEquals(expected, result)

        result = StringUtil.decodeBase64("!!!")
        expected = ""
        assertEquals(expected, result)
    }

    @Test
    fun testEncodeBase64() {
        var result = StringUtil.encodeBase64("light")
        var expected = "bGlnaHQ="
        assertEquals(expected, result)
        result = StringUtil.encodeBase64("light w")
        expected = "bGlnaHQgdw=="
        assertEquals(expected, result)
        result = StringUtil.encodeBase64("light wo")
        expected = "bGlnaHQgd28="
        assertEquals(expected, result)
        result = StringUtil.encodeBase64("light wor")
        expected = "bGlnaHQgd29y"
        assertEquals(expected, result)

        result = StringUtil.encodeBase64("")
        expected = ""
        assertEquals(expected, result)
        result = StringUtil.encodeBase64("123456")
        expected = "MTIzNDU2"
        assertEquals(expected, result)
        result = StringUtil.encodeBase64("@#!%^*()[]")
        expected = "QCMhJV4qKClbXQ=="
        assertEquals(expected, result)
    }

    @Test
    fun `Given a double num Then formatDecimal works`() {
        var input = 3.14
        var result = StringUtil.formatDecimal(input)
        assertEquals("3.14", result)

        input = 0.0
        result = StringUtil.formatDecimal(input)
        assertEquals("0.00", result)

        input = 0.108982
        result = StringUtil.formatDecimal(input)
        assertEquals("0.108982", result)

        input = 0.1234567
        result = StringUtil.formatDecimal(input)
        assertEquals("0.123457", result)

        input = 2.0e-6
        result = StringUtil.formatDecimal(input)
        assertEquals("0.000002", result)
    }

}
