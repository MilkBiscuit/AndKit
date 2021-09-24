package com.cheng.apikit.network.nonpublic

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
internal interface ApiInterface {

    @GET
    suspend fun getApiCall(@Url url: String, @HeaderMap headers: Map<String, String?>): String

    @POST
    suspend fun postApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String?>,
        @Body body: RequestBody
    ): String

    @PUT
    suspend fun putApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String?>,
        @Body body: RequestBody
    ): String

    /**
     * Some delete API call returns a null response body, which triggers Kotlin NullPointerException
     * Declare Response<Unit> as return type
     * Refer to https://github.com/square/retrofit/issues/3075
     */
    @DELETE
    suspend fun deleteApiCall(
        @Url url: String,
        @HeaderMap headers: Map<String, String?>
    ): Response<Unit>

}
