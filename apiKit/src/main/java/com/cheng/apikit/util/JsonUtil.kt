package com.cheng.apikit.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken


object JsonUtil {

    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    fun isValidJsonObject(jsonString: String) : Boolean {
        val json = jsonString.trim()

        return json.startsWith('{') && json.endsWith('}')
    }

    fun objectToJson(input: Any): String {
        return gson.toJson(input)
    }

    inline fun<reified T> jsonToObject(jsonString: String): T? {
        if (isValidJsonObject(jsonString)) {
            return gson.fromJson(jsonString, T::class.java)
        }

        return null
    }

    inline fun<reified T> jsonToObject(jsonString: String, typeAdapter: Any): T? {
        if (isValidJsonObject(jsonString)) {
            val gsonWithAdapter = GsonBuilder().registerTypeAdapter(T::class.java, typeAdapter).create()

            return gsonWithAdapter.fromJson(jsonString, T::class.java)
        }

        return null
    }

    inline fun<reified T> jsonToList(jsonString: String): List<T> {
        val listType = object : TypeToken<List<T>>() {}.type

        return gson.fromJson<List<T>>(jsonString, listType)
    }

}
