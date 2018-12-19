package com.cheng.httpproject.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class InfoodleLoginDetail(@SerializedName("client_secret") val clientSecret: String,
                               @SerializedName("unique_id") val id: String,
                               @SerializedName("firstname") val firstName: String,
                               @SerializedName("lastname") val lastName: String,
                               val email: String,
                               @SerializedName("country_code") val countryCode: String,
                               @SerializedName("country_name") val countryName: String
//                               val url: String
) : Serializable