package com.cheng.httpproject.util

import com.google.gson.Gson
import java.lang.reflect.Type

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

        fun<T> objectToJson(input: Any): String {
            return Gson().toJson(input)
        }

        fun<T> jsonToObject(jsonString: String, type: Type): T? {
            return Gson().fromJson(jsonString, type)
        }

    }
}