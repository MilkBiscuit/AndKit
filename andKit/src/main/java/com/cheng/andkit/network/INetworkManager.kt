package com.cheng.andkit.network

import com.cheng.andkit.network.model.NetworkRequest
import com.cheng.andkit.network.model.NetworkResult

/**
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
interface INetworkManager {

    // Return Success with JSON string OR Failure with exception
    suspend fun getApi(url: String, headers: Map<String, String?>): NetworkResult<String>
    suspend fun postApi(url: String, headers: Map<String, String?>, body: Any): NetworkResult<String>
    suspend fun putApi(url: String, headers: Map<String, String?>, body: Any): NetworkResult<String>
    suspend fun deleteApi(url: String, headers: Map<String, String?>): NetworkResult<String>
    suspend fun sendNetworkRequest(request: NetworkRequest): NetworkResult<String>

}
