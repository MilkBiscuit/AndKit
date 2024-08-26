package com.cheng.andkit.extension

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class StringExtensionTest {

    @Test
    fun `toBool - Given strings that represent true, Then returns true`() {
        listOf(
            "1", "2", "apple", "T", "YeS", "TRUE",
        ).forEach { input ->
            println("testing $input")
            val result = input.toBool()
            assertTrue(result)
        }
    }

    @Test
    fun `toBool - Given strings that represent false, Then returns false`() {
        listOf(
            "0", "NO", "false", "fALSe", "", null,
        ).forEach { input ->
            println("testing $input")
            val result = input.toBool()
            assertFalse(result)
        }
    }

}
