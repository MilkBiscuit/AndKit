package com.cheng.andkit.sample.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class PlexureStore(
    val id: String?,
    val name: String?,
    val address: String?,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val distance: Int = 0,
    val featureList: List<String>? = emptyList(),
)

