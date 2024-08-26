package com.cheng.andkit.util.android

import android.content.Context
import com.cheng.andkit.model.ApikitException

object ContextHolder {
    private const val TAG = "ContextHolder"

    private var appContext: Context? = null

    fun setAppContext(context: Context) {
        appContext = context
    }

    fun getAppContext(): Context {
        return appContext ?: throw ApikitException("Application context has not been set.")
    }
}
