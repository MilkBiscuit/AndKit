package com.cheng.andkit

import android.content.Context
import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.log.internal.TrimmedTree
import com.cheng.andkit.util.android.ContextHolder

object AndKit {

    fun init(appContext: Context, minLogLevel: Int) {
        ContextHolder.setAppContext(appContext)
        Lumberjack.plant(TrimmedTree(minLogLevel))
    }

}
