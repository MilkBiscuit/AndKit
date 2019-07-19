package com.cheng.httpproject.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object PlexureService {

    private val BASE_URL = "https://mopjapaneastgateway.plexure.io/store/v2/"
    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private var service = retrofit.create(PlexureApiInterface::class.java)

    fun getInstance(): PlexureApiInterface {
        return service
    }

}