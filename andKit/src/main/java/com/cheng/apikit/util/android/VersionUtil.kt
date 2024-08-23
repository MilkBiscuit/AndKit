package com.cheng.apikit.util.android

import android.os.Build

object VersionUtil {

    val isAndAboveNougat = { Build.VERSION.SDK_INT >= Build.VERSION_CODES.N }

}