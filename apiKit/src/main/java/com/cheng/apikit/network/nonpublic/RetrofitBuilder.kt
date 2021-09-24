package com.cheng.apikit.network.nonpublic

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object RetrofitBuilder {

    private const val FAKE_BASE_URL = "https://mockapi.io/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(FAKE_BASE_URL)
        .addConverterFactory(StringConverterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(ApiInterface::class.java)

    internal fun getApiService(): ApiInterface {
        return service
    }

}
