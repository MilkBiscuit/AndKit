package com.cheng.andkit.network.model


/**
 * @property httpMethod, GET, POST, PUT, DELETE etc.
 * @property url, the whole url string
 * @property authenticationType, refer to enum class AuthenticationType
 * @property headers, request headers
 * @property body, only applicable for POST, PUT, PATCH method, prevails bodyMap if it is not null
 * @property bodyMap, only applicable for POST, PUT, PATCH method
 */
data class NetworkRequest(
    val httpMethod: String,
    val url: String,
    val headers: Map<String, String?>,
    val body: Any? = null,
    val bodyMap: Map<String, Any?> = emptyMap()
)
