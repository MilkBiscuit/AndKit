package com.cheng.andkit.sample.domain.model

import kotlinx.serialization.SerialName
import java.io.Serializable


@kotlinx.serialization.Serializable
data class CurrentWeatherResponse(
        val id: Long,
        val weather: List<WeatherCondition>?,
        val main: MainData?,
        @SerialName("name")
        val cityName: String?,
        @SerialName("dt")
        val time: Long
): Serializable

@kotlinx.serialization.Serializable
data class WeatherCondition(
        val id: Long,
        val description: String?,
        val icon: String?,
        @SerialName("main")
        val shortDescription: String?
) : Serializable

@kotlinx.serialization.Serializable
data class MainData(
        val humidity: Int?,
        val pressure: Double?,
        val temp: Double?,
        @SerialName("temp_min")
        val minTemp: Double?,
        @SerialName("temp_max")
        val maxTemp: Double?
) : Serializable
