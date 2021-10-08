package com.cheng.apikit.util

import android.util.Base64
import java.io.UnsupportedEncodingException


object StringUtil {

    const val REGEX_HEX_COLOUR = "^#[0-9a-fA-F]{8}\$|#[0-9a-fA-F]{6}\$|#[0-9a-fA-F]{3}\$"
    const val REGEX_DETECT_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
    private const val REGEX_BASE_64 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?\$"

    fun capitaliseFirstLetter(value: String?) : String? {
        if (value == null) {
            return null
        }

        if (value.length > 1) {
            return value[0].toUpperCase() + value.substring(1)
        }

        return value.toUpperCase()
    }

    fun isValidColourHex(value: String?) : Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        val regex = Regex(REGEX_HEX_COLOUR)

        return regex.matches(value)
    }

    fun getUrlsFromString(input: String?) : List<String> {
        if (input == null) {
            return emptyList()
        }

        val urls = mutableListOf<String>()
        val regex = Regex(REGEX_DETECT_URL)
        var matchResult = regex.find(input)
        while (matchResult != null) {
            val url = matchResult.groupValues[0]
            urls.add(url)
            matchResult = matchResult.next()
        }

        return urls
    }

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
        val bytes = Base64.encode(source.toByteArray(), Base64.NO_WRAP)
        var res = ""
        try {
            res = String(bytes, Charsets.UTF_8)
        } catch (ignored: UnsupportedEncodingException) {
        }
        return res
    }

}
