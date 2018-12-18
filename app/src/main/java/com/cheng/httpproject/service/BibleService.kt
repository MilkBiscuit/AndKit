package com.cheng.httpproject.service

import com.cheng.httpproject.constant.BibleConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object BibleService {


    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    private var service = retrofit.create(BibleApiInterface::class.java)

    private fun BibleService() {}

    fun getInstance(): BibleApiInterface {
        return service
    }

}