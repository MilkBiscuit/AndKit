package com.cheng.httpproject.util

class JsonUtil {

    companion object {

        fun isValidJsonObject(jsonString: String) : Boolean {
            val json = jsonString.trim()

            return json.startsWith('{') && json.endsWith('}')
        }

        fun isValidJsonArray(jsonString: String): Boolean {
            val json = jsonString.trim()

            return json.startsWith('[') && json.endsWith(']')
        }

    }
}