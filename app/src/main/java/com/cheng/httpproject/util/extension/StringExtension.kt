package com.cheng.httpproject.util

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

fun String.equalIgnoreCase(compare: String): Boolean {
    return this.equals(compare, true)
}

fun String?.toBool(): Boolean {
    if (this.isNullOrBlank()) {
        return false;
    }
    if (this.equalIgnoreCase("0")
     || this.equalIgnoreCase("N") || this.equalIgnoreCase("no")
     || this.equalIgnoreCase("F") || this.equalIgnoreCase("False")) {
        return false;
    }

    return true;

}
