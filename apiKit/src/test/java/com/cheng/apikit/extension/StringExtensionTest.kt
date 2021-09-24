package com.cheng.apikit.extension

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StringExtensionTest {

    companion object {
        @JvmStatic
        fun boolValues() = listOf(
                Arguments.of("1", true),
                Arguments.of("2", true),
                Arguments.of("apple", true),
                Arguments.of("T", true),
                Arguments.of("YeS", true),
                Arguments.of("TRUE", true),
                Arguments.of("0", false),
                Arguments.of("NO", false),
                Arguments.of("false", false),
                Arguments.of("False", false),
                Arguments.of("", false),
                Arguments.of(null, false)
        )

        @JvmStatic
        fun intValues() = listOf(
                Arguments.of("1", 1),
                Arguments.of("-0", 0),
                Arguments.of("-3", -3),
                Arguments.of("-65536", -65536),
                Arguments.of("999999999", 999999999),
                Arguments.of("-89797", -89797),
                Arguments.of("AAA", 0),
                Arguments.of("NO", 0),
                Arguments.of("1.0", 0)
        )

        @JvmStatic
        fun doubleValues() = listOf(
                Arguments.of("1.0", 1.0),
                Arguments.of("0.454545454545", 0.454545454545),
                Arguments.of("-3", -3.0),
                Arguments.of("1.414", 1.414),
                Arguments.of("1234.3333333", 1234.3333333),
                Arguments.of("AAA", 0.0),
                Arguments.of("NO", 0.0),
                Arguments.of("1234.5555588.0", 0.0)
        )
    }

    @Test
    fun testEqualIgnoreCase() {
        var expected = "Apple"
        var testString = "apple"
        assertTrue(testString.equalIgnoreCase(expected))
        testString = "aPPle"
        assertTrue(testString.equalIgnoreCase(expected))
        testString = "APPlE"
        assertTrue(testString.equalIgnoreCase(expected))

        expected = ""
        testString = ""
        assertTrue(testString.equalIgnoreCase(expected))
    }

    @ParameterizedTest
    @MethodSource("boolValues")
    fun testToBool(input: String?, expected: Boolean) {
        val result = input.toBool()
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("intValues")
    fun testParseInt(input: String, expected: Int) {
        val result = input.parseIntNum()
        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("doubleValues")
    fun testParseDouble(input: String, expected: Double) {
        val result = input.parseDoubleNum()
        assertTrue(expected == result)
    }

}
