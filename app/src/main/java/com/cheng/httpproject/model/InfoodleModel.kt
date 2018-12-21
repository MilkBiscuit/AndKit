package com.cheng.httpproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class InfoodleLoginDetail(@SerializedName("client_secret") val clientSecret: String,
                               @SerializedName("unique_id") val id: String,
                               @SerializedName("firstname") val firstName: String,
                               @SerializedName("lastname") val lastName: String,
                               val email: String,
                               @SerializedName("country_code") val countryCode: String,
                               @SerializedName("country_name") val countryName: String
) : Serializable

@Parcelize
data class InfoodleSearchPersonItem(@SerializedName("unique_id") val id: String,
                                    @SerializedName("title") val title: String,
                                    @SerializedName("firstname") val firstName: String,
                                    @SerializedName("lastname") val lastName: String
) : Parcelable

data class InfoodleSearchPersonResult(val people: List<InfoodleSearchPersonItem>?)
