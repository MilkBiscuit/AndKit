package com.cheng.httpproject.helper

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.cheng.httpproject.constant.PrefConstants
import com.cheng.httpproject.oauth2.OAuth2Constants
import com.cheng.httpproject.oauth2.OAuth2Detail
import com.cheng.httpproject.util.JsonUtil
import com.cheng.httpproject.util.SingletonHolder
import com.google.gson.reflect.TypeToken
import java.util.*


class SharedPrefHelper private constructor(context: Context) {

    private val KEY_OAUTH2_DETAIL = "Oauth2Detail"

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

    fun saveOAuth2LoginDetail(clientId: String, clientSecret: String) {
        saveString(OAuth2Constants.CLIENT_ID, clientId)
        saveString(OAuth2Constants.CLIENT_SECRET, clientSecret)
    }

    fun saveOAuth2Detail(detail: OAuth2Detail) {
        val responseString = JsonUtil.objectToJson<OAuth2Detail>(detail)

        saveString(KEY_OAUTH2_DETAIL, responseString)
    }

    fun getOAuth2Properties(): OAuth2Detail {
        val responseStr = getString(KEY_OAUTH2_DETAIL)
        val type = object : TypeToken<OAuth2Detail>() {}.type
        val response = JsonUtil.jsonToObject<OAuth2Detail>(responseStr, type)
        if (response == null) {
            val clientId = getString(OAuth2Constants.CLIENT_ID)
            val clientSecret = getString(OAuth2Constants.CLIENT_SECRET)

            return OAuth2Detail(clientId, clientSecret, "", "",
                    "", 0L)
        }

        return response
    }

}