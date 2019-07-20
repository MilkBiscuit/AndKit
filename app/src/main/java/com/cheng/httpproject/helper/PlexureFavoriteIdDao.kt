package com.cheng.httpproject.helper

import android.content.Context
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.model.PlexureFavoriteStoreId
import com.cheng.httpproject.util.SingletonHolder
import io.realm.Realm

class PlexureFavoriteIdDao private constructor(context: Context) {

    companion object: SingletonHolder<PlexureFavoriteIdDao, Context>(::PlexureFavoriteIdDao)

    fun toggleFavorite(id: String) {
        val realm = Realm.getDefaultInstance()
        var plexureFavoriteStoreId = realm.where(PlexureFavoriteStoreId::class.java)
                .equalTo(PlexureConstants.FIELD_ID, id)
                .findFirst()
        realm.beginTransaction()
        if (plexureFavoriteStoreId != null) {
            plexureFavoriteStoreId.deleteFromRealm()
        } else {
            plexureFavoriteStoreId = PlexureFavoriteStoreId(id)
            realm.copyToRealm(plexureFavoriteStoreId)
        }
        realm.commitTransaction()
        realm.close()
    }

}