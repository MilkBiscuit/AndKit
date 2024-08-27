package com.cheng.andkit.log.internal

import com.cheng.andkit.log.Lumberjack

internal class TrimmedTree(
    private val minLogLevel: Int,
): Lumberjack.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority < minLogLevel) {
            return
        }

        super.log(priority, tag, message, t)
    }

}
