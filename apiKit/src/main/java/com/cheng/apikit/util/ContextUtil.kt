package com.cheng.apikit.util

import android.app.Application
import android.content.Context
import com.cheng.apikit.ApikitException

object ContextUtil {
    private const val TAG = "ContextUtil"

    private var sAppContext: Context? = null

    /**
     * Sets the [Application] that will be used throughout the Apikit
     */
    fun setAppContext(context: Context) {
        sAppContext = context
    }

    /**
     * @return The application [Context] that is used throughout the Apikit.
     */
    fun getAppContext(): Context {
        return sAppContext ?: throw ApikitException("Application context has not been set.")
    }
}
