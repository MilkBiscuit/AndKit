package com.cheng.httpproject.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cheng.httpproject.model.PlexureStore
import io.realm.Realm
import io.realm.RealmChangeListener

class PlexureStoreViewModel : ViewModel() {

    private val realm = Realm.getDefaultInstance()
    private val realmQuery = realm.where(PlexureStore::class.java).sort("name")
    private val stores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
            val results = realmQuery.findAll()
            it.value = results.toList()
            results.addChangeListener(RealmChangeListener { newResults ->
                stores.value = newResults.toList()
            })
        }
    }
    private val favoriteStores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
            val results = realmQuery.lessThan("distance", 10000).findAll()
            it.value = results
            results.addChangeListener(RealmChangeListener { newResults ->
                favoriteStores.value = newResults.toList()
            })
        }
    }

    override fun onCleared() {
        super.onCleared()

        realm.close()
    }

    fun getAllStores(): LiveData<List<PlexureStore>> {
        return stores
    }

    fun getFavoriteStores(): LiveData<List<PlexureStore>> {
        return favoriteStores
    }

}