package com.cheng.andkit.sample.ui.store

enum class StoreType(val value: Int) {
    All(0),
    Favorite(1);

    companion object {
        fun valueOf(value: Int): StoreType? = entries.firstOrNull { it.value == value }
    }
}
