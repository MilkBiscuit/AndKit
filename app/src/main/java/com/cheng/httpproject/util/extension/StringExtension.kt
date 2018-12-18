package com.cheng.httpproject

import android.util.Log

private const val TAG = "Extension"

fun String.parseDoubleNum(): Double {
    var value = 0.0
    try {
        value = this.toDouble()
    } catch (e: Exception) {
        Log.w(TAG, "parseDoubleNum error: ${this}")
    }

    return value;
}

fun String.parseIntNum(): Int {
    var value = 0
    try {
        value = this.toInt()
    } catch (e: Exception) {
        Log.w(TAG, "parseIntNum error: ${this}")
    }

    return value;
}
