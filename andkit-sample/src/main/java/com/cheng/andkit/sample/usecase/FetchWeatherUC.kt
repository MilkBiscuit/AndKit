package com.cheng.andkit.sample.usecase

import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.network.RestApiHelper
import com.cheng.andkit.sample.domain.model.CurrentWeatherResponse
import com.cheng.andkit.util.JsonUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchWeatherUC {

    suspend operator fun invoke(cityName: String): CurrentWeatherResponse? {
        return withContext(Dispatchers.IO) {
            val url = "https://api.openweathermap.org/data/2.5/weather?units=metric&apikey=$WEATHER_API_KEY&q=$cityName"
            val result = RestApiHelper.getApi(url, emptyMap())
            if (result.isSuccess) {
                val jsonString = result.getOrElse { "" }

                return@withContext JsonUtil.jsonToObject<CurrentWeatherResponse>(jsonString)
            }
            Lumberjack.w("FetchWeather failed: ${result.exceptionOrNull()}")

            return@withContext null
        }
    }

    companion object {
        private const val WEATHER_API_KEY = "9f234208b8a660c47018bff2fe9d0fd2"
    }

}
