package com.cheng.apikit.extension

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class StringExtensionTest {

    @ParameterizedTest
    @MethodSource("boolValues")
    fun testToBool(input: String?, expected: Boolean) {
        val result = input.toBool()
        Assertions.assertEquals(expected, result)
    }

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

}
