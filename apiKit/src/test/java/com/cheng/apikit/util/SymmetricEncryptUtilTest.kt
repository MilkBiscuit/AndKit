package com.cheng.apikit.util

import com.cheng.apikit.util.security.SymmetricEncryptUtil
import org.junit.Assert
import org.junit.Test
import org.robolectric.annotation.Config

@Config(manifest= Config.NONE)
class SymmetricEncryptUtilTest {

    @Test
    fun testEncrypt() {
        var encryptedResult = SymmetricEncryptUtil.encrypt("Hello World", "123456")
        Assert.assertEquals("9at1l20/przDf2xNYBrzdQ==", encryptedResult)

        encryptedResult = SymmetricEncryptUtil.encrypt("", "123456")
        Assert.assertEquals("kVd2XaZ/Ne5Op34TUjfFOQ==", encryptedResult)
        encryptedResult = SymmetricEncryptUtil.encrypt("Hello World", "")
        Assert.assertEquals("uqkPULOpnFuJoBU0UuYq0w==", encryptedResult)
    }

    @Test
    fun testDecrypt() {
        var decryptedResult = SymmetricEncryptUtil.decrypt("9at1l20/przDf2xNYBrzdQ==", "123456")
        Assert.assertEquals("Hello World", decryptedResult)

        decryptedResult = SymmetricEncryptUtil.decrypt("kVd2XaZ/Ne5Op34TUjfFOQ==", "123456")
        Assert.assertEquals("", decryptedResult)
        decryptedResult = SymmetricEncryptUtil.decrypt("uqkPULOpnFuJoBU0UuYq0w==", "")
        Assert.assertEquals("Hello World", decryptedResult)
    }

    @Test
    fun testAdjustLength() {
        var input = "123456"
        var result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("1234", result)
        input = "1234"
        result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("1234", result)
        input = "12"
        result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("1200", result)
        input = ""
        result = SymmetricEncryptUtil.adjustLength(input, 4)
        Assert.assertEquals("0000", result)

        input = "12"
        result = SymmetricEncryptUtil.adjustLength(input, 0)
        Assert.assertEquals("", result)
        result = SymmetricEncryptUtil.adjustLength(input, -1)
        Assert.assertEquals("", result)
    }

}
