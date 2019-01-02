package com.cheng.httpproject.model

import java.io.Serializable

import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
        val id: Long,
        val weather: List<WeatherCondition>?,
        val main: MainData?,
        @SerializedName("name")
        val cityName: String?,
        @SerializedName("dt")
        val time: Long
): Serializable

data class WeatherCondition(
        val id: Long,
        val description: String?,
        val icon: String?,
        @SerializedName("main") val shortDescription: String?
) : Serializable

data class MainData(
        val humidity: Int?,
        val pressure: Double?,
        val temp: Double?,
        @SerializedName("temp_min")
        val minTemp: Double?,
        @SerializedName("temp_max")
        val maxTemp: Double?
) : Serializable