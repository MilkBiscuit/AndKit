package com.cheng.httpproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PrefKeyConstants
import com.cheng.httpproject.constant.PrimitiveConstants
import com.cheng.httpproject.ui.activity.MainActivity
import com.cheng.httpproject.ui.activity.SettingsActivity
import kotlin.system.exitProcess

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_settings)

        findPreference<ListPreference>(PrefKeyConstants.PREF_KEY_LANGUAGE)!!.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        when(preference.key) {
            PrefKeyConstants.PREF_KEY_LANGUAGE -> {
                val settingsActivity = activity as SettingsActivity
                settingsActivity.showLoading()
                Handler().postDelayed({restartApp()}, PrimitiveConstants.MILLISECONDS_PER_SECOND.toLong())
            }
        }

        return true
    }

    fun restartApp() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)

        exitProcess(0)
    }


}
