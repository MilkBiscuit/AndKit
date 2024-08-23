package com.cheng.apikit.util

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.math.RoundingMode
import java.text.DecimalFormat


object StringUtil {

    const val REGEX_HEX_COLOUR = "^#[0-9a-fA-F]{8}\$|#[0-9a-fA-F]{6}\$|#[0-9a-fA-F]{3}\$"
    const val REGEX_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
    private const val REGEX_BASE_64 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?\$"

    fun capitaliseFirstLetter(value: String?) : String? {
        if (value == null) {
            return null
        }

        if (value.length > 1) {
            return value[0].uppercase() + value.substring(1)
        }

        return value.uppercase()
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
        val regex = Regex(REGEX_URL)
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

        return matchesRegex(value, REGEX_BASE_64)
    }

    fun matchesRegex(input: String, regString: String): Boolean {
        val regex = Regex(regString)
        return regex.matches(input)
    }

    fun decodeBase64(value: String): String {
        val decoded = try {
            java.util.Base64.getDecoder().decode(value)
        } catch (e: IllegalArgumentException) {
            byteArrayOf()
        }
        return String(decoded, Charsets.UTF_8)
    }

    fun encodeBase64(source: String): String {
        return java.util.Base64.getEncoder().encodeToString(source.toByteArray())
    }

    private val decimalFormat = DecimalFormat().apply {
        roundingMode = RoundingMode.HALF_UP
        maximumFractionDigits = 6
        minimumFractionDigits = 2
        isGroupingUsed = true
    }
    /** Format double [value] to string, without the scientific notation */
    fun formatDecimal(value: Double): String? = decimalFormat.format(value)

}
