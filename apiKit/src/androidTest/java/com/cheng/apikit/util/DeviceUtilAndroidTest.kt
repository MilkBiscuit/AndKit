package com.cheng.apikit.util

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DeviceUtilAndroidTest {

    private val context: Context by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Before
    fun setup() {
        ContextUtil.setAppContext(context)
    }

    @Test
    fun test_PureUnitTest_WhenRunningAndroidTest() {
        Assert.assertFalse(DeviceUtil.isRunningPureUnitTest())
    }

    @Test
    fun test_GetScreenResolutionInPxString_HasValue() {
        val screenResolutionString = DeviceUtil.getScreenResolutionInPxString()
        Assert.assertTrue(screenResolutionString.length > 1)
        Assert.assertTrue(screenResolutionString.contains('x'))
    }

    @Test
    fun print_DeviceScreenSize() {
        val message = "Screen size in dp is ${DeviceUtil.screenWidthInDp}" +
                " by ${DeviceUtil.screenHeightInDp}"
        println(message)
        Assert.assertTrue(message, true)
    }

}
