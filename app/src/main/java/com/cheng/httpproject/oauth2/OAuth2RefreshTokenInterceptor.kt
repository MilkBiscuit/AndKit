package com.cheng.httpproject.oauth2

import android.util.Log
import com.cheng.httpproject.constant.ApiConstants
import okhttp3.Interceptor
import okhttp3.Response

class OAuth2RefreshTokenInterceptor(private val oAuth2Detail: OAuth2Detail) : Interceptor {

    private val TAG = "RefreshTokenInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val code = response?.code()
        when(code) {
            ApiConstants.HTTP_UNAUTHORIZED, ApiConstants.HTTP_FORBIDDEN -> {
                val builder = chain.request().newBuilder()
                if (oAuth2Detail.refreshToken.isEmpty() || oAuth2Detail.clientId.isEmpty()
                        || oAuth2Detail.clientSecret.isEmpty()) {
                    Log.w(TAG, "refresh token failed: $oAuth2Detail")
                } else {
                    builder.addHeader(OAuth2Constants.ACCESS_TOKEN, oAuth2Detail.refreshToken)
                    builder.addHeader(OAuth2Constants.CLIENT_ID, oAuth2Detail.clientId)
                    builder.addHeader(OAuth2Constants.CLIENT_SECRET, oAuth2Detail.clientSecret)
                    builder.addHeader(OAuth2Constants.GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN)

                    return chain.proceed(builder.build())
                }
            }
        }

        return response
    }

}