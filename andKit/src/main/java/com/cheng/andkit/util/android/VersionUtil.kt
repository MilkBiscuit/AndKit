package com.cheng.andkit.util.android

import android.os.Build

object VersionUtil {

    val isAndAboveNougat = { Build.VERSION.SDK_INT >= Build.VERSION_CODES.N }

}