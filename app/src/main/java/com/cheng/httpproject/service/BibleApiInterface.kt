package com.cheng.httpproject.service

import com.cheng.httpproject.model.BibleModelResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface BibleApiInterface {

    @GET("bibles/{bibleId}/chapters/{chapterId}?content-type=text")
    fun fetchChapter(@HeaderMap headers: Map<String, String>, @Path("bibleId") bibleId: String,
                     @Path("chapterId") chapterId: String): Observable<BibleModelResult>

}