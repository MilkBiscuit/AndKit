package com.cheng.httpproject.util

class StringUtil {
    companion object {

        const val REGEX_HEX_COLOUR = "^#(?:[0-9a-fA-F]{3}){1,2}\$"
        const val REGEX_DETECT_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"

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

    }
}