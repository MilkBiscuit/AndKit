package com.cheng.andkit.sample.ui.activity.weather

import androidx.lifecycle.ViewModel
import com.cheng.andkit.sample.domain.model.CurrentWeatherResponse
import com.cheng.andkit.sample.usecase.FetchWeatherUC
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class WeatherViewModel: ViewModel() {

    private val _userInputFlow: MutableSharedFlow<String> = MutableStateFlow("")
    val userInputFlow: SharedFlow<String> = _userInputFlow.asSharedFlow()

    private val fetchWeatherUC: FetchWeatherUC = FetchWeatherUC()

    fun onUserInputChanged(newText: String) {
        _userInputFlow.tryEmit(newText)
    }

    suspend fun getWeatherData(cityName: String): CurrentWeatherResponse? =
        fetchWeatherUC.invoke(cityName)

}
