package com.cheng.httpproject.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.activity.base.BaseActivity

class MainActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle(R.string.app_name)
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.btn_bible -> launchBibleActivity()
            R.id.btn_infoodle -> launchInfoodleActivity()
            R.id.btn_weather -> launchWeatherActivity()
            R.id.btn_plexure -> launchPlexureActivity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun launchBibleActivity() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("valeera://gizmos")
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

    fun launchPlexureActivity() {
        val intent = Intent(this, StoreListActivity::class.java)
        startActivity(intent)
    }

}
