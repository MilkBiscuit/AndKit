package com.cheng.httpproject.ui.activity

//import kotlinx.android.synthetic.main.activity_weather.*
import android.os.Bundle
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.cheng.apikit.network.NetworkManager
import com.cheng.apikit.network.model.Failure
import com.cheng.apikit.network.model.Success
import com.cheng.apikit.util.JsonUtil
import com.cheng.httpproject.BuildConfig
import com.cheng.httpproject.R
import com.cheng.httpproject.databinding.ActivityWeatherBinding
import com.cheng.httpproject.model.CurrentWeatherResponse
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.fragment.CurrentWeatherFragment
import com.cheng.httpproject.util.applySchedulers
import com.cheng.httpproject.util.debounceOneSecond
import io.reactivex.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherActivity : BaseActivity(), OnQueryTextListener {

    private lateinit var binding: ActivityWeatherBinding

    var userInputSubject = BehaviorSubject.create<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_weather)

        binding.svCityName.setOnQueryTextListener(this)

        startObserve()
    }

    override fun onQueryTextSubmit(s: String?): Boolean {
        userInputSubject.onNext(s ?: "")

        return true
    }

    override fun onQueryTextChange(s: String?): Boolean {
        userInputSubject.onNext(s ?: "")

        return true
    }

    private fun startObserve() {
        val subject = userInputSubject.filter { it.length >= 2 }.debounceOneSecond()
        var disposable = subject.applySchedulers().doOnNext{
            showLoading()
        }.subscribe()
        addDisposable(disposable)

        disposable = subject.subscribe { cityName ->
            CoroutineScope(Dispatchers.Default).launch {
                val url = "https://api.openweathermap.org/data/2.5/weather?units=metric&apikey=${BuildConfig.WEATHER_API_KEY}&q=$cityName"
                val result = NetworkManager.getApi(url, emptyMap())

                withContext(Dispatchers.Main) {
                    hideLoading()
                    when (result) {
                        is Success -> {
                            val weatherResponse = JsonUtil.jsonToObject<CurrentWeatherResponse>(result.value)!!
                            val fragment = CurrentWeatherFragment.newInstance(weatherResponse)
                            replaceFragment(R.id.layout_current_weather, fragment)
                        }
                        is Failure -> {
                            showToast(result.exception.localizedMessage ?: "Something is wrong.")
                        }
                    }
                }
            }
        }
        addDisposable(disposable)
    }

}
