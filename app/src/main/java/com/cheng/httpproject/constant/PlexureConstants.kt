package com.cheng.httpproject.constant

object PlexureConstants {

    const val FIELD_ID = "id"
    const val FIELD_NAME = "name"
    const val FIELD_DISTANCE = "distance"

    enum class StoreType(val value: Int) {
        All(0), Favorite(1);

        companion object {
            fun valueOf(value: Int): StoreType? = values().first { it.value == value }
        }
    }

}

