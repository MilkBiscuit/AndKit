package com.cheng.apikit.extension

private const val TAG = "Extension"

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
