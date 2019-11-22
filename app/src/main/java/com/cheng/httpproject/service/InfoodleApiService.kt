package com.cheng.httpproject.service

import android.content.Context
import com.cheng.httpproject.oauth2.OAuth2Authenticator
import com.cheng.httpproject.oauth2.OAuth2Constants
import com.cheng.httpproject.helper.SingletonHolder
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

const val BASE_URL:String = "https://app-t.infoodle.com/apiv2/";

class InfoodleApiService private constructor(context: Context) {

    private val context = context.applicationContext

    companion object: SingletonHolder<InfoodleApiService, Context>(::InfoodleApiService)

    fun getService(): InfoodleApiInterface {
        val retrofit = Retrofit.Builder()
                .client(getOAuthOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(InfoodleApiInterface::class.java)
    }

    private fun getOAuthOkHttpClient(): OkHttpClient {
        val myCacheDir = File(context.cacheDir, OAuth2Constants.OAUTH2_CACHE)
        val cacheSize = 1024 * 1024L
        val cacheDir = Cache(myCacheDir, cacheSize)
        val oAuth2Authenticator = OAuth2Authenticator(context)
        return OkHttpClient.Builder()
                .cache(cacheDir)
                .authenticator(oAuth2Authenticator)
                .build()
    }

}