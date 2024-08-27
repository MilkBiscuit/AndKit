package com.cheng.andkit.network.model


/**
 * @property httpMethod, GET, POST, PUT, DELETE etc.
 * @property url, the whole url
 * @property headers, request headers
 * @property body, only applicable for POST, PUT, PATCH method
 */
data class RestApiRequest(
    val httpMethod: String,
    val url: String,
    val headers: Map<String, String?>,
    val body: String? = null,
)
