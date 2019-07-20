package com.cheng.httpproject.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class PlexureStore(
        @PrimaryKey var id: String? = null,
        var name: String? = null,
        var address: String? = null,
        var latitude: Double = 0.0,
        var longitude: Double = 0.0,
        var distance: Int = 0,
        var featureList: RealmList<String>? = null
) : RealmObject()

open class PlexureFavoriteStoreId(
        @PrimaryKey var id: String = ""
) : RealmObject()

