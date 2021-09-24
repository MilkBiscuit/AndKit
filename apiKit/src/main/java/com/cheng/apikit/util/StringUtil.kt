package com.cheng.apikit.util

import android.util.Base64
import java.io.UnsupportedEncodingException


object StringUtil {

    private const val REGEX_BASE_64 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?\$"

    fun isBase64Format(value: String?): Boolean {
        if (value.isNullOrBlank()) {
            return value == ""
        }

        return matchesRegx(value, REGEX_BASE_64)
    }

    fun matchesRegx(input: String, regString: String): Boolean {
        val regex = Regex(regString)
        return regex.matches(input)
    }

    fun decodeBase64(value: String): String {
        val decoded = Base64.decode(value, Base64.DEFAULT)
        return String(decoded, Charsets.UTF_8)
    }

    fun encodeBase64(source: String): String {
        val bytes = Base64.encode(source.toByteArray(), Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING)
        var res = ""
        try {
            res = String(bytes, Charsets.UTF_8)
        } catch (ignored: UnsupportedEncodingException) {
        }
        return res
    }

}
