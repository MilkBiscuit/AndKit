package com.cheng.httpproject.model

data class PlexureStore(val id: String,
                        val name: String,
                        val address: String,
                        val latitude: Double,
                        val longitude: Double,
                        val distance: Int,
                        val featureList: List<String>)


