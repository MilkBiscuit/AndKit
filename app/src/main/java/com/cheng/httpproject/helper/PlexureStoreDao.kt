package com.cheng.httpproject.helper

import android.content.Context
import com.cheng.httpproject.model.PlexureStore
import io.realm.Realm

class PlexureStoreDao private constructor(context: Context) {

    companion object: SingletonHolder<PlexureStoreDao, Context>(::PlexureStoreDao)

    fun addOrUpdate(plexureStores: List<PlexureStore>) {
        val realm = Realm.getDefaultInstance()
        realm.use {
            it.beginTransaction()
            it.copyToRealmOrUpdate(plexureStores)
            it.commitTransaction()
        }
    }

}