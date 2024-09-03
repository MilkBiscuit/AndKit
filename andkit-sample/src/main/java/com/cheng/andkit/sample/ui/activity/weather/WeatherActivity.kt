package com.cheng.andkit.sample.ui.activity.weather

import android.os.Bundle
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.ViewModelProvider
import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.databinding.ActivityWeatherBinding
import com.cheng.andkit.sample.ui.activity.BaseActivity
import com.cheng.andkit.sample.ui.fragment.CurrentWeatherFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class WeatherActivity : BaseActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        binding.svCityName.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                viewModel.onUserInputChanged(s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                viewModel.onUserInputChanged(s)
                return true
            }
        })
        setContentView(binding.root)

        listenToUserInput()
    }

    @OptIn(FlowPreview::class)
    private fun listenToUserInput() {
        MainScope().launch(Dispatchers.IO) {
            viewModel.userInputFlow.debounce(500L).collect { cityName ->
                Lumberjack.d("Receive user input: $cityName")
                val weatherResponse = viewModel.getWeatherData(cityName)
                    ?: return@collect

                val fragment = CurrentWeatherFragment.newInstance(weatherResponse)
                replaceFragment(R.id.layout_current_weather, fragment)
            }
        }
    }

}
