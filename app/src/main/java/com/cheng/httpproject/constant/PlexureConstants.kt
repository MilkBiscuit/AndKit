package com.cheng.httpproject.constant

object PlexureConstants {

    enum class StoreType(val value: Int) {
        All(0), Favorite(1);

        companion object {
            fun valueOf(value: Int): StoreType? = values().first { it.value == value }
        }
    }

}

