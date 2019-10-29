package com.cheng.httpproject.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PrefKeyConstants
import com.cheng.httpproject.ui.activity.SettingsActivity
import java.util.*

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.fragment_settings)

        findPreference<ListPreference>(PrefKeyConstants.PREF_KEY_LANGUAGE)!!.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
        when(preference.key) {
            PrefKeyConstants.PREF_KEY_LANGUAGE -> {
                restartActivity()
            }
        }

        return true
    }

    fun restartActivity() {
        val intent = Intent(context, SettingsActivity::class.java)
        startActivity(intent)
        activity!!.finish()
    }


}
