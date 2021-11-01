package com.cheng.httpproject.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.cheng.httpproject.constant.PrefConstants
import java.util.*


class SharedPrefHelper private constructor(context: Context) {

    companion object: SingletonHolder<SharedPrefHelper, Context>(::SharedPrefHelper)

    val sharedPrefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.applicationContext)

    fun saveString(prefKey: String, prefValue: String) {
        val editor = sharedPrefs.edit()
        editor.putString(prefKey, prefValue)
        editor.apply()
    }

    fun getString(prefKey: String, defaultValue: String = ""): String =
            sharedPrefs.getString(prefKey, defaultValue)?: defaultValue

    fun getCurrentLanguage(): String {
        return getString(PrefConstants.PREF_KEY_LANGUAGE)
    }

    fun getCurrentLocale(): Locale {
        return when(getCurrentLanguage()) {
            PrefConstants.VALUE_LANGUAGE_SIMPLIFIED_CHINESE -> Locale.SIMPLIFIED_CHINESE
            PrefConstants.VALUE_LANGUAGE_TRADITIONAL_CHINESE -> Locale.TRADITIONAL_CHINESE
            else -> Locale.ENGLISH
        }
    }

    fun getCurrentTheme(): String {
        return getString(PrefConstants.PREF_KEY_THEME)
    }

}
