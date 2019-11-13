package com.cheng.httpproject.model

import android.os.Parcelable
import com.cheng.httpproject.constant.PxParams
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Represents an offer loaded to the system, and contains all the information for it.
 *
 * @author Chandler Cheng (chandler.cheng@plexure.com)
 */
@Parcelize
data class PxOffer(
    @SerializedName(PxParams.KEY_ID)
    var id: Int = 0,
    @SerializedName(PxParams.KEY_TITLE)
    var title: String? = null,
    @SerializedName(PxParams.KEY_DESCRIPTION)
    var description: String? = null,
    @SerializedName(PxParams.KEY_MERCHANT_ID)
    var merchantId: Int = 0,
    @SerializedName(PxParams.KEY_CONTENT_URL)
    var contentUrl: String? = null,
    @SerializedName(PxParams.KEY_START_DATE)
    var startDate: Date? = null,
    @SerializedName(PxParams.KEY_END_DATE)
    var endDate: Date? = null,
    @SerializedName(PxParams.KEY_DAILY_START_TIME)
    var dailyStartTime: Int? = null,
    @SerializedName(PxParams.KEY_DAILY_END_TIME)
    var dailyEndTime: Int? = null,
    @SerializedName(PxParams.KEY_DAYS_OF_WEEK)
    var daysOfWeekAvailable: List<Int>? = null,
    @SerializedName(PxParams.KEY_IMAGE)
    var imageName: String? = null,
    @SerializedName(PxParams.KEY_IMAGE_DESCRIPTION)
    var imageDescription: String? = null,
    @SerializedName(PxParams.KEY_CONTENT_TAG_REFERENCE_CODES)
    var tags: List<String>? = null,
    @SerializedName(PxParams.KEY_IS_ACTIVE)
    var active: Boolean? = null,
    @SerializedName(PxParams.KEY_IS_AVAILABLE_ALL_STORES)
    var isAvailableInAllVenues: Boolean? = null,
    @SerializedName(PxParams.KEY_DISTANCE_TO_CLOSEST_VENUE)
    var distanceToClosestVenue: Float? = null,
    @SerializedName(PxParams.KEY_EXTENDED_DATA)
    var extendedData: String? = null,

    @SerializedName(PxParams.KEY_OFFER_INSTANCE_UNIQUE_ID)
    var instanceId: String? = null,
    @SerializedName(PxParams.KEY_IMAGE_ALT)
    var altImageName: String? = null,
    @SerializedName(PxParams.KEY_IMAGE_ALT_DESCRIPTION)
    var altImageDescription: String? = null,
    @SerializedName(PxParams.KEY_IS_MERCHANT_FAVOURITE)
    var isFavourite: Boolean = false,
    @SerializedName(PxParams.KEY_LAST_UPDATED_AT)
    var lastUpdateDate: Date? = null,
    @SerializedName(PxParams.KEY_CATEGORY_ID)
    var categoryId: Int = 0,
    @SerializedName(PxParams.KEY_CATEGORY_NAME)
    var categoryName: String? = null,
    @SerializedName(PxParams.KEY_SORT_ORDER)
    var position: Int = 0,
    @SerializedName(PxParams.KEY_IS_PREMIUM_PLACEMENT)
    var premiumPlacement: Boolean = false,
    @SerializedName(PxParams.KEY_PAYMENT_AMOUNT)
    var paymentAmount: Double = 0.toDouble(),
    @SerializedName(PxParams.KEY_PAYMENT_TAX_RATE)
    var paymentTaxRate: Double = 0.toDouble(),
    @SerializedName(PxParams.KEY_REDEMPTION_COUNT)
    var redemptionCount: Int = 0,
    @SerializedName(PxParams.KEY_LAST_REDEEMED_AT)
    var lastRedemptionDate: Date? = null,
    @SerializedName(PxParams.KEY_CODE_EXPIRY_IN_MINUTES)
    var redemptionCodeValidTime: Int? = null,
    @SerializedName(PxParams.KEY_POINT_VALUE)
    var pointValue: Int? = null,
    @SerializedName(PxParams.KEY_BURNT_COUNT)
    var burntCount: Int = 0,
    @SerializedName(PxParams.KEY_LAST_BURNT_AT)
    var lastBurnDate: Date? = null,
    @SerializedName(PxParams.KEY_WEIGHTING)
    var weight: Int? = null,
    @SerializedName(PxParams.KEY_IS_GIFTABLE)
    var isGiftable: Boolean = false,
    @SerializedName(PxParams.KEY_IS_A_GIFT)
    var isGift: Boolean = false,
    @SerializedName(PxParams.KEY_GIFTED_BY)
    var giftSender: String? = null,
    @SerializedName(PxParams.KEY_GIFTED_COPY)
    var giftMessage: String? = null,
    @SerializedName(PxParams.KEY_GIFT_ID)
    var giftId: String? = null,
    @SerializedName(PxParams.KEY_GIFT_BATCH_ID)
    var giftBatchId: String? = null,
    @SerializedName(PxParams.KEY_GIFTED_ON_DATE)
    var giftedDate: Date? = null,
    @SerializedName(PxParams.KEY_TERMS_AND_CONDITIONS)
    var termsAndConditions: String? = null,
    @SerializedName(PxParams.KEY_IS_REWARD)
    var reward: Boolean? = null,
    @SerializedName(PxParams.KEY_IS_REPEATABLE_OFFER)
    var isRepeatable: Boolean? = null,
    /**
     * Number of days that should take for this offer to be available again, once redeemed.
     */
    @SerializedName(PxParams.KEY_REPEAT_DAYS_NUMBER)
    var repeatDaysNumber: Int? = null,
    @SerializedName(PxParams.KEY_REPEAT_LIMIT)
    var repeatLimit: Int? = null,
    @SerializedName(PxParams.KEY_VENUE_IDS)
    var venueIds: List<Int>? = null,
    @SerializedName(PxParams.KEY_VENUE_EXTERNAL_IDS)
    var venueExternalIds: List<String>? = null

) : Parcelable {

    constructor(): this(
            id = 220,
            description = "qqq",
            title = "aaa",
            startDate = Date(2019, 11, 1),
            endDate = Date(2020, 11, 1),
            venueIds = listOf(2417)
    )

}

