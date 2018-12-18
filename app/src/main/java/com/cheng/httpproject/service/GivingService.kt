package com.cheng.httpproject.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object GivingService {

    const val BASE_URL:String = "https://vodapi.ezystream.com/v4/";

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private var service = retrofit.create(GivingApiInterface::class.java)

    private fun GivingService() {}

    fun getInstance(): GivingApiInterface {
        return service
    }

}