package com.cheng.andkit.util

import com.cheng.andkit.log.Lumberjack
import kotlinx.serialization.SerializationException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


object JsonUtil {

    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        prettyPrint = true
    }

    inline fun<reified T> objectToJson(input: T): String {
        return json.encodeToString(input)
    }

    inline fun<reified T> jsonToObject(jsonString: String): T? {
        return try {
            json.decodeFromString(jsonString)
        } catch (e: SerializationException) {
            Lumberjack.w("jsonToObject serialization exception: $e")
            null
        } catch (e: IllegalArgumentException) {
            Lumberjack.w("jsonToObject illegal argument: $e")
            null
        }
    }

    inline fun<reified T> jsonToList(jsonString: String): List<T> {
        return jsonToObject<List<T>>(jsonString) ?: emptyList()
    }

}
