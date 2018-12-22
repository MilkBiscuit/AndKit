package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import ca.mimic.oauth2library.OAuth2Client
import com.cheng.httpproject.R
import com.cheng.httpproject.helper.SharedPrefHelper
import com.cheng.httpproject.oauth2.OAuth2Constants
import com.cheng.httpproject.oauth2.OAuth2Detail
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.fragment.InfoodleAuthFragment
import com.cheng.httpproject.ui.fragment.InfoodleDirectoryFragment
import com.cheng.httpproject.ui.fragment.base.BaseFragment

class InfoodleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_infoodle)

        val data = intent.data
        val sharedPrefHelper = SharedPrefHelper.getInstance(this)
        val authProperty = sharedPrefHelper.getOAuth2Properties()
        var fragment: BaseFragment = InfoodleAuthFragment();
        if (OAuth2Constants.REDIRECT_URI_ROOT == data?.scheme) {
            val authorizationCode = data.getQueryParameter(OAuth2Constants.CODE)

            val parameter = mapOf(
                    OAuth2Constants.REDIRECT_URI to OAuth2Constants.REDIRECT_URI_VALUE,
                    OAuth2Constants.RESPONSE_TYPE to "token",
                    OAuth2Constants.CODE to authorizationCode)
            val client = OAuth2Client.Builder(
                    "", "", authProperty.clientId, authProperty.clientSecret,
                    OAuth2Constants.REQUEST_TOKEN_URL)
                    .grantType(OAuth2Constants.AUTHORIZATION_CODE)
                    .parameters(parameter)
                    .build()
            client.requestAccessToken{ response ->
                if (response.isSuccessful) {
                    val detail = OAuth2Detail(
                            authProperty.clientId, authProperty.clientSecret, response.accessToken,
                            response.refreshToken, response.tokenType, response.expiresAt)
                    sharedPrefHelper.saveOAuth2Detail(detail)
                    fragment = InfoodleDirectoryFragment()
                }

                replaceFragment(fragment)
            }

            return
        }
        if (authProperty.accessToken.isNotEmpty()) {
            fragment = InfoodleDirectoryFragment()
        }

        replaceFragment(fragment)
    }

    fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.infoodle_fragment, fragment)
    }

}
