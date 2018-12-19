package com.cheng.httpproject.service

import com.cheng.httpproject.model.InfoodleLoginDetail
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InfoodleApiInterface {

    @FormUrlEncoded
    @POST("oauth2/userauthorise")
    fun fetchClientSecret(@Field("username") username: String,
                          @Field("password") password: String):
            Observable<InfoodleLoginDetail>

}