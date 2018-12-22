package com.cheng.httpproject.service

import com.cheng.httpproject.model.InfoodleLoginDetail
import com.cheng.httpproject.model.InfoodleSearchPersonResult
import com.cheng.httpproject.oauth2.RefreshTokenBody
import com.cheng.httpproject.oauth2.RefreshTokenResult
import io.reactivex.Observable
import retrofit2.http.*

interface InfoodleApiInterface {

    @FormUrlEncoded
    @POST("oauth2/userauthorise")
    fun fetchClientSecret(@Field("username") username: String,
                          @Field("password") password: String):
            Observable<InfoodleLoginDetail>

    @GET("people/search/name/{key}")
    fun searchPerson(@Path("key") key: String):
            Observable<InfoodleSearchPersonResult>

    @POST("oauth2/token")
    fun refreshToken(@Body refreshTokenBody: RefreshTokenBody): Observable<RefreshTokenResult>

}