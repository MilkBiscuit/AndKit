package com.cheng.andkit.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class StringUtilTest {

    @Test
    fun `parseUrlFromString - returns correct value`() {
        val url = "http://www.google.com"
        var testString = "Please visit $url to book our latest events"

        var resultList = StringUtil.getUrlsFromString(testString)
        assertTrue(resultList.isNotEmpty())
        assertEquals(url, resultList[0])


        val url2 = "http://www.baidu.com"
        testString = "#$url2 is not recommended, please use $url instead $$## "

        resultList = StringUtil.getUrlsFromString(testString)
        assertTrue(resultList.isNotEmpty())
        assertEquals(url2, resultList[0])
        assertEquals(url, resultList[1])
    }

    @Test
    fun `capitaliseFirstLetter - Given alphabetic strings - Then capitalise the first letter correctly`() {
        var result = StringUtil.capitaliseFirstLetter("apple")
        assertEquals("Apple", result)

        result = StringUtil.capitaliseFirstLetter("omg")
        assertEquals("Omg", result)

        result = StringUtil.capitaliseFirstLetter("aPPle")
        assertEquals("APPle", result)
    }

    @Test
    fun `capitaliseFirstLetter - Given first letter is not a letter - Then returns original string`() {
        var result = StringUtil.capitaliseFirstLetter("134#$%&")
        assertEquals("134#$%&", result)

        result = StringUtil.capitaliseFirstLetter("≠ banana")
        assertEquals("≠ banana", result)

        result = StringUtil.capitaliseFirstLetter("中文字符")
        assertEquals("中文字符", result)

        result = StringUtil.capitaliseFirstLetter("  ")
        assertEquals("  ", result)

        result = StringUtil.capitaliseFirstLetter(null)
        assertEquals(null, result)
    }

    @Test
    fun `isValidColourHex - Given valid color hex strings - Then returns true`() {
        val validColorHexStrings = listOf("#FFF", "#000", "#abc", "#778899", "#ffffffff")
        validColorHexStrings.forEach { input ->
            println("Testing: $input")
            val result = StringUtil.isValidColourHex(input)
            assertTrue(result)
        }
    }

    @Test
    fun `isValidColourHex - Given invalid color hex strings - Then returns true`() {
        val invalidColorHexStrings = listOf("7F7F7F", "#0000", "#12345", "#Apple", "", null)
        invalidColorHexStrings.forEach { input ->
            println("Testing: $input")
            val result = StringUtil.isValidColourHex(input)
            assertFalse(result)
        }
    }

    @Test
    fun `isBase64Format - Given valid base64 strings - Then returns true`() {
        val validBase64Strings = listOf("", "bGlnaHQgdw==", "MzUzOTc3NTgz")
        validBase64Strings.forEach { input ->
            println("Testing: $input")
            val result = StringUtil.isBase64Format(input)
            assertTrue(result)
        }
    }

    @Test
    fun `isBase64Format - Given invalid base64 strings - Then returns false`() {
        val invalidBase64Strings = listOf(null, " ", "\t", "APP")
        invalidBase64Strings.forEach { input ->
            println("Testing: $input")
            val result = StringUtil.isBase64Format(input)
            assertFalse(result)
        }
    }

    @Test
    fun `decodeBase64 - Given valid base64 strings, Then decode successfully`() {
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
    fun `encodeBase64 - returns encoded string successfully`() {
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
    fun `formatDecimal - Given a double number, Then returns correct value`() {
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
