package com.cheng.andkit.network

import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.network.internal.RetrofitBuilder
import com.cheng.andkit.network.model.HttpMethods
import com.cheng.andkit.network.model.RestApiRequest
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException

object RestApiHelper {

    private val service = RetrofitBuilder.getApiService()
    private val mediaType = MediaType.parse("application/json; charset=utf-8")

    suspend fun getApi(url: String, headers: Map<String, String?>): Result<String> {
        return runCatching {
            service.getApiCall(url, headers)
        }.onFailure(action = ::handleException)
    }

    suspend fun postApi(url: String, headers: Map<String, String?>, bodyJson: String?): Result<String> {
        return runCatching {
            val requestBody = RequestBody.create(mediaType, bodyJson ?: "")
            service.postApiCall(url, headers, requestBody)
        }.onFailure(action = ::handleException)
    }

    suspend fun putApi(url: String, headers: Map<String, String?>, bodyJson: String?): Result<String> {
        return runCatching {
            val requestBody = RequestBody.create(mediaType, bodyJson ?: "")
            service.putApiCall(url, headers, requestBody)
        }.onFailure(action = ::handleException)
    }

    suspend fun deleteApi(url: String, headers: Map<String, String?>): Result<String> {
        return runCatching {
            service.deleteApiCall(url, headers)

            ""
        }.onFailure(action = ::handleException)
    }

    suspend fun sendNetworkRequest(request: RestApiRequest): Result<String> {
        // Pre-check internet connection
        return sendRequest(request, request.headers)
    }

    private fun handleException(throwable: Throwable) {
        if (throwable is HttpException) {
            val errorBody = throwable.response()?.errorBody()
            Lumberjack.e("Http status code: ${throwable.code()}, response: $errorBody")
        }
        Lumberjack.e("Error: ${throwable.localizedMessage}")
    }

    private suspend fun sendRequest(
        request: RestApiRequest, headerMap: Map<String, String?>
    ) = when {
        HttpMethods.POST.equals(request.httpMethod, true) -> {
            postApi(request.url, headerMap, request.body)
        }
        HttpMethods.PUT.equals(request.httpMethod, true) -> {
            putApi(request.url, headerMap, request.body)
        }
        HttpMethods.DELETE.equals(request.httpMethod, true) -> {
            deleteApi(request.url, headerMap)
        }
        HttpMethods.GET.equals(request.httpMethod, true) -> {
            getApi(request.url, headerMap)
        }
        else -> throw IllegalStateException("${request.httpMethod} http method not implemented yet!")
    }

}
