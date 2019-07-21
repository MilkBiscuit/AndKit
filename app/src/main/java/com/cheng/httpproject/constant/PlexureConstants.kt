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

