package com.cheng.httpproject.constant

object PlexureConstants {

    const val FIELD_ID = "id"

    const val FEATURE_WIFI = "FREE_WIFI"
    const val FEATURE_DRIVE_THROUGH = "DRIVETHR"
    const val FEATURE_BP = "BP"
    const val FEATURE_BF = "BF"
    const val FEATURE_MC_ADVENT = "MCADVENT"
    const val FEATURE_PLAY_LAND = "PLAYLAND"
    const val FEATURE_TABLE = "TABLE_DELIVERY"
    const val GREY_DISTANT_STORES: Boolean = true
    const val GREY_DISTANCE_IN_METER = 1000000
    const val DEFAULT_LATITUDE = 26.333351598841787
    const val DEFAULT_LONGITUDE = 127.79896146273005

    enum class StoreType(val value: Int) {
        All(0), Favorite(1);

        companion object {
            fun valueOf(value: Int): StoreType? = values().first { it.value == value }
        }
    }

    enum class SortMethod {
        NEAREST, FURTHERMOST, NAME
    }

}

