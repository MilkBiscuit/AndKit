package com.cheng.andkit.network

import android.util.Log
import com.cheng.andkit.network.model.*
import com.cheng.andkit.network.internal.RetrofitBuilder
import com.cheng.andkit.util.JsonUtil
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException
import java.lang.IllegalStateException

object NetworkManager : INetworkManager {

    private const val TAG = "NetworkManager"
    private val service = RetrofitBuilder.getApiService()
    private val mediaType = MediaType.parse("application/json; charset=utf-8")

    override suspend fun getApi(url: String, headers: Map<String, String?>): NetworkResult<String> {
        return try {
            val response = service.getApiCall(url, headers)

            Success(response)
        } catch (e : Exception) {
            handleException("getApi", url, e)

            Failure(e)
        }
    }

    override suspend fun postApi(url: String, headers: Map<String, String?>, body: Any): NetworkResult<String> {
        val bodyJson = JsonUtil.objectToJson(body)
        return try {
            val requestBody = RequestBody.create(mediaType, bodyJson)
            val response = service.postApiCall(url, headers, requestBody)

            Success(response)
        } catch (e: Exception) {
            handleException("postApi", url, e)

            Failure(e)
        }
    }

    override suspend fun putApi(url: String, headers: Map<String, String?>, body: Any): NetworkResult<String> {
        val bodyJson = JsonUtil.objectToJson(body)
        return try {
            val requestBody = RequestBody.create(mediaType, bodyJson)
            val response = service.putApiCall(url, headers, requestBody)

            Success(response)
        } catch (e: Exception) {
            handleException("postApi", url, e)

            Failure(e)
        }
    }

    override suspend fun deleteApi(url: String, headers: Map<String, String?>): NetworkResult<String> {
        return try {
            service.deleteApiCall(url, headers)

            Success("")
        } catch (e : Exception) {
            handleException("deleteApi", url, e)

            Failure(e)
        }
    }

    override suspend fun sendNetworkRequest(request: NetworkRequest): NetworkResult<String> {
        // Pre-check internet connection
        return sendRequest(request, request.headers)
    }

    private fun handleException(methodName: String, url: String, e: Exception) {
        if (e is HttpException) {
            val errorBody = e.response()?.errorBody()
            Log.e(TAG, "$methodName, error code: ${e.code()}, response: $errorBody")
        }
        Log.e(TAG, "$methodName, url: $url, exception ${e.localizedMessage}")
    }

    private suspend fun sendRequest(
        request: NetworkRequest, headerMap: Map<String, String?>
    ) = when {
        HttpMethods.POST.equals(request.httpMethod, true) -> {
            postApi(request.url, headerMap, request.body ?: request.bodyMap)
        }
        HttpMethods.PUT.equals(request.httpMethod, true) -> {
            putApi(request.url, headerMap, request.body ?: request.bodyMap)
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
