package com.cheng.httpproject.oauth2

import android.content.Context
import com.cheng.httpproject.helper.SharedPrefHelper
import com.cheng.httpproject.util.parseIntNum
import com.cheng.httpproject.service.InfoodleApiService
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class OAuth2Authenticator(val context: Context) : Authenticator {

    @Throws(IOException::class)
    override fun authenticate(route: Route?, response: Response): Request? {
        val infoodleApiService = InfoodleApiService.getInstance(context).getService()
        val sharedPrefHelper = SharedPrefHelper.getInstance(context)
        val oauth2Prop = sharedPrefHelper.getOAuth2Properties()
        val now = System.currentTimeMillis()
        if (oauth2Prop.accessToken.isEmpty()) {
            return null
        } else if (oauth2Prop.expireAt <= now) {
            val body = RefreshTokenBody(oauth2Prop.refreshToken, oauth2Prop.clientId,
                    oauth2Prop.clientSecret)
            val result = infoodleApiService.refreshToken(body).blockingFirst()

            oauth2Prop.accessToken = result.access_token
            oauth2Prop.refreshToken = result.refresh_token
            oauth2Prop.expireAt = result.expires_in.parseIntNum() * 1000L + now
            sharedPrefHelper.saveOAuth2Detail(oauth2Prop)
        }

        // Add new header to rejected request and retry it
        return response.request().newBuilder()
                .header(OAuth2Constants.AUTHORIZATION, "${oauth2Prop.tokenType} ${oauth2Prop.accessToken}")
                .build()
    }

}