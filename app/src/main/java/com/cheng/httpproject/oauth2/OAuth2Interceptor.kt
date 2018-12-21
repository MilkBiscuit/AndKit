package com.cheng.httpproject.oauth2

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class OAuth2Interceptor(private val accessToken: String?, private val accessTokenType: String?)
    : Interceptor {

    private val TAG = "OAuth2Interceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (accessToken.isNullOrEmpty() || accessTokenType.isNullOrEmpty()) {
            Log.w(TAG, "access token $accessToken, access token type $accessTokenType")
        } else {
            builder.addHeader(OAuth2Constants.AUTHORIZATION, "$accessTokenType $accessToken")
        }

        return chain.proceed(builder.build())
    }

}