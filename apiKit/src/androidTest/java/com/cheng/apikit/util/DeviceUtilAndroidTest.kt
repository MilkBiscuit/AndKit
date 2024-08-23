package com.cheng.apikit.util

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cheng.apikit.util.android.ContextHolder
import com.cheng.apikit.util.android.DeviceUtil
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
        ContextHolder.setAppContext(context)
    }

    @Test
    fun whenRunning_ThenIsRunningUnitTestReturnsFalse() {
        Assert.assertFalse(DeviceUtil.isRunningUnitTest())
    }

    @Test
    fun givenOneVirtualDevice_ThenGetScreenResolutionInPxStringReturnsValue() {
        val screenResolutionString = DeviceUtil.getScreenResolutionInPxString()
        val message = "Screen size in px is $screenResolutionString"
        println(message)
        Assert.assertTrue(screenResolutionString.length > 1)
        Assert.assertTrue(screenResolutionString.contains('x'))
    }

    @Test
    fun givenOneVirtualDevice_ThenHasNavigationBarReturnsTrue() {
        val hasNavigationBar = DeviceUtil.hasNavigationBar()
        Assert.assertTrue(hasNavigationBar)
    }

    @Test
    fun printDeviceScreenSizeToConsole() {
        val message = "Screen size in dp is ${DeviceUtil.screenWidthInDp}" +
                " x ${DeviceUtil.screenHeightInDp}"
        println(message)
        Assert.assertTrue(message, true)
    }

}
