package com.cheng.httpproject.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PrefConstants
import com.cheng.httpproject.helper.SharedPrefHelper

/**
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
object ContextUtil {

    fun createConfigurationContext(context: Context): Context {
        return if (VersionUtil.isAndAboveNougat()) {
            val configuration = createConfiguration(context)
            context.createConfigurationContext(configuration)
        } else {
            context
        }
    }

    fun updateLocale(context: Context) {
        // createConfigurationContext is not working on Samsung S6 (Android 7.0), use below method for all versions
//        if (!VersionUtil.isAndAboveNougat()) {
            val resources = context.resources
            val configuration = createConfiguration(context)
            resources.updateConfiguration(configuration, resources.displayMetrics);
//        }
    }

    fun updateTheme(activity: Activity) {
        val currentTheme = SharedPrefHelper.getInstance(activity).getCurrentTheme()
        if (currentTheme.isEmpty() || currentTheme == PrefConstants.DEF_VALUE_THEME) {
            activity.setTheme(R.style.AppTheme)
        } else {
            activity.setTheme(R.style.AppThemeBlackWhite)
        }
    }

    private fun createConfiguration(context: Context): Configuration {
        val configuration = context.resources.configuration
        val sharedPrefHelper = SharedPrefHelper.getInstance(context)
        configuration.setLocale(sharedPrefHelper.getCurrentLocale())

        return configuration
    }

}