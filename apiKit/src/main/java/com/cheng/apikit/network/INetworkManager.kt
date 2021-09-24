package com.cheng.apikit.network

import com.cheng.apikit.network.model.NetworkResult

/**
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
interface INetworkManager {

    suspend fun getApi(url: String, headers: Map<String, String?>): NetworkResult<String>
    suspend fun postApi(url: String, headers: Map<String, String?>, body: Any): NetworkResult<String>
    suspend fun putApi(url: String, headers: Map<String, String?>, body: Any): NetworkResult<String>
    suspend fun deleteApi(url: String, headers: Map<String, String?>): NetworkResult<String>

}
