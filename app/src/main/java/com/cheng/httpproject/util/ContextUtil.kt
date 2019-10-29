package com.cheng.httpproject.util

import android.content.Context
import android.content.res.Configuration
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
        if (!VersionUtil.isAndAboveNougat()) {
            val resources = context.resources
            val configuration = createConfiguration(context)
            resources.updateConfiguration(configuration, resources.displayMetrics);
        }
    }

    private fun createConfiguration(context: Context): Configuration {
        val configuration = context.resources.configuration
        val sharedPrefHelper = SharedPrefHelper.getInstance(context)
        configuration.setLocale(sharedPrefHelper.getCurrentLocale())

        return configuration
    }

}