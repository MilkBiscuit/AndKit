package com.cheng.apikit.util

import android.app.Application
import android.content.Context
import com.cheng.apikit.ToolkitException

object ContextUtil {
    private const val TAG = "ContextUtil"

    private var sApp: Application? = null
    private var sAppContext: Context? = null

    /**
     * Sets the [Application] that will be used throughout the Toolkit
     */
    fun setApplication(app: Application) {
        sApp = app
        sAppContext = app.applicationContext
    }

    /**
     * Sets the [Application] that will be used throughout the Toolkit
     */
    fun setAppContext(context: Context) {
        sAppContext = context
    }

    /**
     * @return The application [Context] that is used throughout the Toolkit.
     */
    fun getAppContext(): Context {
        return sAppContext ?: throw ToolkitException("Application context has not been set.")
    }
}
