package com.cheng.apikit.extension

private const val TAG = "Extension"

fun String?.toBool(): Boolean {
    if (this.isNullOrBlank()) {
        return false;
    }
    if (this.equalsIgnoreCase("0")
     || this.equalsIgnoreCase("N") || this.equalsIgnoreCase("no")
     || this.equalsIgnoreCase("F") || this.equalsIgnoreCase("False")) {
        return false;
    }

    return true;

}

private fun String.equalsIgnoreCase(compare: String): Boolean {
    return this.equals(compare, true)
}
