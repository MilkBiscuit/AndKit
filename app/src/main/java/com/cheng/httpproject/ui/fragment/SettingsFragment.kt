package com.cheng.httpproject.ui.fragment

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PrefConstants
import com.cheng.httpproject.helper.SharedPrefHelper
import com.cheng.httpproject.ui.activity.SettingsActivity

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_settings)

        val languagePref = findPreference<ListPreference>(PrefConstants.PREF_KEY_LANGUAGE)!!
        val themePref = findPreference<ListPreference>(PrefConstants.PREF_KEY_THEME)!!
        languagePref.onPreferenceChangeListener = this
        themePref.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        val sharedPrefHelper = SharedPrefHelper.getInstance(context!!)
        when(preference.key) {
            PrefConstants.PREF_KEY_LANGUAGE -> {
                // If same value is selected, do nothing
                if (sharedPrefHelper.getCurrentLanguage() != newValue) {
                    val settingsActivity = activity as SettingsActivity
                    settingsActivity.showLoading()
                }
            }
            PrefConstants.PREF_KEY_THEME -> {
                if (sharedPrefHelper.getCurrentTheme() != newValue) {
                    val settingsActivity = activity as SettingsActivity
                    settingsActivity.showLoading()
                }
            }
        }

        return true
    }

}
