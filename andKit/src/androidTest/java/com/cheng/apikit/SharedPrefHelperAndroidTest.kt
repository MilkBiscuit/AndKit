package com.cheng.apikit

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cheng.andkit.util.android.ContextHolder
import com.cheng.andkit.util.android.SharedPrefHelper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPrefHelperAndroidTest {

    companion object {
        const val KEY_FOR_TEST = "StringKeyForTest"
    }

    private val context: Context by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext
    }
    private val sharedPreferences: SharedPreferences by lazy {
        ContextHolder.setAppContext(context)
        SharedPrefHelper.getEncryptedSharedPref("ApiKitTest")!!
    }

    @Before
    fun setup() {
        sharedPreferences.edit(true) {
            clear()
        }
    }

    @Test
    fun testGetSetString() {
        var result = sharedPreferences.getString(KEY_FOR_TEST, "")
        assertEquals("", result)

        sharedPreferences.edit(true) {
            putString(KEY_FOR_TEST, "HelloWorld")
        }
        result = sharedPreferences.getString(KEY_FOR_TEST, "") ?: ""
        assertEquals("HelloWorld", result)
    }

}
