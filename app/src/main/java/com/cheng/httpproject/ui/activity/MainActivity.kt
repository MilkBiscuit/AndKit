package com.cheng.httpproject.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.activity.base.BaseActivity

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.btn_bible -> launchBibleActivity()
            R.id.btn_infoodle -> launchInfoodleActivity()
            R.id.btn_weather -> launchWeatherActivity()
        }
    }

    fun launchBibleActivity() {
        val intent = Intent(this, BibleActivity::class.java)
        startActivity(intent)
    }

    fun launchWeatherActivity() {
        val intent = Intent(this, WeatherActivity::class.java)
        startActivity(intent)
    }

    fun launchInfoodleActivity() {
        val intent = Intent(this, InfoodleActivity::class.java)
        startActivity(intent)
    }

}
