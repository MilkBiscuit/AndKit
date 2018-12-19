package com.cheng.httpproject.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object InfoodleApiService {

    const val BASE_URL:String = "https://app-t.infoodle.com/apiv2/";

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private var service = retrofit.create(InfoodleApiInterface::class.java)

    private fun InfoodleApiService() {}

    fun getInstance(): InfoodleApiInterface {
        return service
    }

}