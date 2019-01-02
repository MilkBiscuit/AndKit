package com.cheng.httpproject.service

import com.cheng.httpproject.BuildConfig
import com.cheng.httpproject.model.CurrentWeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET("weather?units=metric&apikey=" + BuildConfig.WEATHER_API_KEY)
    fun fetchCurrentWeather(@Query("q") cityName: String): Observable<CurrentWeatherResponse>
}