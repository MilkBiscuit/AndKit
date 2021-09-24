package com.cheng.apikit.network

import android.util.Log
import com.cheng.apikit.network.model.Failure
import com.cheng.apikit.network.model.NetworkResult
import com.cheng.apikit.network.model.Success
import com.cheng.apikit.network.nonpublic.RetrofitBuilder
import com.cheng.apikit.util.JsonUtil
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.HttpException

/**
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
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

    private fun handleException(methodName: String, url: String, e: Exception) {
        if (e is HttpException) {
            val errorBody = e.response()?.errorBody()
            Log.e(TAG, "$methodName, error code: ${e.code()}, response: $errorBody")
        }
        Log.e(TAG, "$methodName, url: $url, exception ${e.localizedMessage}")
    }

}
