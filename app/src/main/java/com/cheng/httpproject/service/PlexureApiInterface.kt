package com.cheng.httpproject.service

import com.cheng.httpproject.model.PlexureStore
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PlexureApiInterface {
    @GET("stores?radius=100000000&size=100")
    fun fetchStores(
            @Query("latitude") latitude: Double,
            @Query("longitude") longitude: Double
                    ): Observable<List<PlexureStore>>
}