package com.cheng.andkit.sample.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.domain.model.CurrentWeatherResponse
import com.cheng.andkit.util.DateUtil
import java.util.*

class CurrentWeatherFragment : Fragment() {

    private var cityName: TextView? = null
    private var countryName: TextView? = null
    private var temperatureView: TextView? = null
    private var weatherConditionView: TextView? = null
    private var dateView: TextView? = null
    private var minTemperatureTv: TextView? = null
    private var maxTemperatureTv: TextView? = null
    private var currentWeather: CurrentWeatherResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(KEY_CURRENT_WEATHER)) {
                currentWeather = it.getSerializable(KEY_CURRENT_WEATHER) as CurrentWeatherResponse
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_current_weather, container, false)
        countryName = view.findViewById(R.id.detail_country)
        cityName = view.findViewById(R.id.detail_city)
        temperatureView = view.findViewById(R.id.detail_temperature)
        weatherConditionView = view.findViewById(R.id.detail_weather_condition)
        dateView = view.findViewById(R.id.current_date)
        minTemperatureTv = view.findViewById(R.id.today_min_temperature)
        maxTemperatureTv = view.findViewById(R.id.today_max_temperature)

        updateUI()

        return view
    }

    private fun updateUI() {
        currentWeather?.let {
            countryName?.text = it.sysData.country
            cityName?.text = it.cityName

            val weatherCondition = it.weather!![0]
            weatherConditionView?.text = weatherCondition.shortDescription

            val main = it.main!!
            val currentTemp = main.temp?.toInt() ?: 0
            val minTemp = main.minTemp?.toInt() ?: 0
            val maxTemp = main.maxTemp?.toInt() ?: 0

            val context = requireContext()
            temperatureView?.text = context.getString(R.string.temperature, currentTemp)
            minTemperatureTv?.text = context.getString(R.string.temperature, minTemp)
            maxTemperatureTv?.text = context.getString(R.string.temperature, maxTemp)

            val epochTimeInMillis = it.time * 1000
            val date = Date(epochTimeInMillis)
            dateView?.text = DateUtil.getWeekday(date)
        }
    }

    companion object {
        private const val KEY_CURRENT_WEATHER = "CurrentWeather"

        fun newInstance(response: CurrentWeatherResponse) : CurrentWeatherFragment {
            val fragment = CurrentWeatherFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_CURRENT_WEATHER, response)
                }
            }

            return fragment
        }
    }

}
