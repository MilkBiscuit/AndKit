package com.cheng.httpproject.ui.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PrimitiveConstants
import com.cheng.httpproject.helper.ResStringHelper
import com.cheng.httpproject.model.CurrentWeatherResponse
import com.cheng.httpproject.util.DateUtil
import java.util.*

class CurrentWeatherFragment : Fragment() {

    companion object {
        private val KEY_CURRENT_WEATHER = "CurrentWeather"

        fun newInstance(response: CurrentWeatherResponse) : CurrentWeatherFragment {
            val fragment = CurrentWeatherFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_CURRENT_WEATHER, response)
                }
            }

            return fragment
        }
    }

    private var cityName: TextView? = null
    private var temperatureView: TextView? = null
    private var weatherConditionView: TextView? = null
    private var dateView: TextView? = null
    private var minTemperatureTv: TextView? = null
    private var maxTemperatureTv: TextView? = null
    private var currentWeather: CurrentWeatherResponse? = null
    private lateinit var stringHelper: ResStringHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(KEY_CURRENT_WEATHER)) {
                currentWeather = it.getSerializable(KEY_CURRENT_WEATHER) as CurrentWeatherResponse
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        stringHelper = ResStringHelper.getInstance(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_current_weather, container, false)
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
            cityName?.text = it.cityName

            val weatherCondition = it.weather!![0]
            weatherConditionView?.text = weatherCondition.shortDescription

            val main = it.main!!
            val currentTemp = main.temp?.toInt() ?: 0
            val minTemp = main.minTemp?.toInt() ?: 0
            val maxTemp = main.maxTemp?.toInt() ?: 0

            temperatureView?.text = stringHelper.getString(R.string.temperature, currentTemp)
            minTemperatureTv?.text = stringHelper.getString(R.string.temperature, minTemp)
            maxTemperatureTv?.text = stringHelper.getString(R.string.temperature, maxTemp)

            val time = it.time * PrimitiveConstants.MILLISECONDS_PER_SECOND
            val date = Date(time)
            dateView?.text = DateUtil.getWeekday(date)
        }
    }



}