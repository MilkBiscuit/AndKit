package com.cheng.httpproject.util

import com.cheng.httpproject.util.*
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

}
