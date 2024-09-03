package com.cheng.andkit.sample.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.cheng.andkit.network.model.Failure
import com.cheng.andkit.network.model.Success
import com.cheng.andkit.sample.databinding.ActivityWeatherBinding
import com.cheng.andkit.sample.domain.model.CurrentWeatherResponse
import com.cheng.andkit.util.JsonUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherActivity : BaseActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        binding.svCityName.setOnQueryTextListener(this)
        setContentView(binding.root)

        startObserve()
    }

    override fun onQueryTextSubmit(s: String?): Boolean {
//        userInputSubject.onNext(s ?: "")

        return true
    }

    override fun onQueryTextChange(s: String?): Boolean {
//        userInputSubject.onNext(s ?: "")

        return true
    }

    private fun startObserve() {
//        val subject = userInputSubject.filter { it.length >= 2 }.debounceOneSecond()
//        var disposable = subject.applySchedulers().doOnNext{
//            showLoading()
//        }.subscribe()
//        addDisposable(disposable)
//
//        disposable = subject.subscribe { cityName ->
//            CoroutineScope(Dispatchers.Default).launch {
//                val url = "https://api.openweathermap.org/data/2.5/weather?units=metric&apikey=${BuildConfig.WEATHER_API_KEY}&q=$cityName"
//                val result = NetworkManager.getApi(url, emptyMap())
//
//                withContext(Dispatchers.Main) {
//                    hideLoading()
//                    when (result) {
//                        is Success -> {
//                            val weatherResponse = JsonUtil.jsonToObject<CurrentWeatherResponse>(result.value)!!
//                            val fragment = CurrentWeatherFragment.newInstance(weatherResponse)
//                            replaceFragment(R.id.layout_current_weather, fragment)
//                        }
//                        is Failure -> {
//                            showToast(result.exception.localizedMessage ?: "Something is wrong.")
//                        }
//                    }
//                }
//            }
//        }
//        addDisposable(disposable)
    }

}
