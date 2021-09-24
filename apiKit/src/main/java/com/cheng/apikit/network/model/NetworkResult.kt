package com.cheng.apikit.network.model

sealed class NetworkResult<out T>

data class Success<out T>(val value: T): NetworkResult<T>()
data class Failure<T>(val exception: Exception): NetworkResult<T>()
