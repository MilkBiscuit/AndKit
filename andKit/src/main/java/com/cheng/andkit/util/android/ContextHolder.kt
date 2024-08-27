package com.cheng.andkit.util.android

import android.content.Context
import com.cheng.andkit.model.AndKitException

object ContextHolder {
    private var appContext: Context? = null

    fun setAppContext(context: Context) {
        appContext = context
    }

    fun getAppContext(): Context {
        return appContext ?: throw AndKitException("Application context has not been set.")
    }
}
