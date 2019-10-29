package com.cheng.httpproject.util

import android.os.Build

/**
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
object VersionUtil {

    val isAndAboveNougat = { Build.VERSION.SDK_INT >= Build.VERSION_CODES.N }

}