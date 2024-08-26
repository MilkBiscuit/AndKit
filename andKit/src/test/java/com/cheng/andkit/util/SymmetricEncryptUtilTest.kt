package com.cheng.andkit.util

import com.cheng.andkit.util.security.SymmetricEncryptUtil
import org.junit.Assert
import org.junit.Test


class SymmetricEncryptUtilTest {

    @Test
    fun `encrypt - Given a password, Then encrypts the input string correctly`() {
        var encryptedResult = SymmetricEncryptUtil.encrypt("Hello World", "123456")
        Assert.assertEquals("9at1l20/przDf2xNYBrzdQ==", encryptedResult)

        encryptedResult = SymmetricEncryptUtil.encrypt("", "123456")
        Assert.assertEquals("kVd2XaZ/Ne5Op34TUjfFOQ==", encryptedResult)
        encryptedResult = SymmetricEncryptUtil.encrypt("Hello World", "")
        Assert.assertEquals("uqkPULOpnFuJoBU0UuYq0w==", encryptedResult)
    }

    @Test
    fun `decrypt - Given the correct password, Then the original text should be decrypted correctly`() {
        var decryptedResult = SymmetricEncryptUtil.decrypt("9at1l20/przDf2xNYBrzdQ==", "123456")
        Assert.assertEquals("Hello World", decryptedResult)

        decryptedResult = SymmetricEncryptUtil.decrypt("kVd2XaZ/Ne5Op34TUjfFOQ==", "123456")
        Assert.assertEquals("", decryptedResult)
        decryptedResult = SymmetricEncryptUtil.decrypt("uqkPULOpnFuJoBU0UuYq0w==", "")
        Assert.assertEquals("Hello World", decryptedResult)
    }

    @Test
    fun `adjustLength - Given input is too long, Then truncates it`() {
        var input = "123456"
        var result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("1234", result)
        input = "1234"
        result = SymmetricEncryptUtil.adjustLength(input, 2)
        Assert.assertEquals("12", result)

        input = "12"
        result = SymmetricEncryptUtil.adjustLength(input, 0)
        Assert.assertEquals("", result)
        result = SymmetricEncryptUtil.adjustLength(input, -1)
        Assert.assertEquals("", result)
    }

    @Test
    fun `adjustLength - Given input is too short, Then pads 0 at the end`() {
        var input = "12"
        var result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("1200", result)
        input = ""
        result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("0000", result)
    }

    @Test
    fun `adjustLength - Given input length equals the provided length, Then returns original string`() {
        var input = "5678"
        var result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("5678", result)
        input = ""
        result = SymmetricEncryptUtil.adjustLength(input, 0)
        Assert.assertEquals("", result)
    }

}
