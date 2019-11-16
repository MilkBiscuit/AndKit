package com.cheng.httpproject.util

import com.google.gson.GsonBuilder
import java.lang.reflect.Type

object JsonUtil {

    val gson = GsonBuilder().setPrettyPrinting().create()

    fun isValidJsonObject(jsonString: String) : Boolean {
        val json = jsonString.trim()

        return json.startsWith('{') && json.endsWith('}')
    }

    fun isValidJsonArray(jsonString: String): Boolean {
        val json = jsonString.trim()

        return json.startsWith('[') && json.endsWith(']')
    }

    fun objectToJson(input: Any): String {
        return gson.toJson(input)
    }

    fun<T> jsonToObject(jsonString: String, type: Type): T? {
        return gson.fromJson(jsonString, type)
    }

}
