package com.cheng.httpproject.helper

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.cheng.httpproject.oauth2.OAuth2Constants
import com.cheng.httpproject.oauth2.OAuth2Detail
import com.cheng.httpproject.util.JsonUtil
import com.cheng.httpproject.util.SingletonHolder
import com.google.gson.reflect.TypeToken

private const val KEY_OAUTH2_DETAIL = "Oauth2Detail"

class SharedPrefHelper private constructor(context: Context) {

    companion object: SingletonHolder<SharedPrefHelper, Context>(::SharedPrefHelper)

    private val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveString(prefKey: String, prefValue: String) {
        val editor = sharedPref.edit()
        editor.putString(prefKey, prefValue)
        editor.apply()
    }

    fun getString(prefKey: String, defaultValue: String = ""): String =
            sharedPref.getString(prefKey, defaultValue)?: defaultValue

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