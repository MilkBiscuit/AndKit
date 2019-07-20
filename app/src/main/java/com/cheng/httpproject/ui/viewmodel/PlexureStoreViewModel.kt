package com.cheng.httpproject.ui.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.model.PlexureFavoriteStoreId
import com.cheng.httpproject.model.PlexureStore
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

class PlexureStoreViewModel : ViewModel() {

    private val realm = Realm.getDefaultInstance()
    private val favoriteIdResults = realm.where(PlexureFavoriteStoreId::class.java).findAll()

    private var favoriteStoreResults: RealmResults<PlexureStore>? = null
    private var favoriteStoreIds: List<String> = favoriteIdResults.toList().map { i -> i.id }

    init {
        favoriteIdResults.addChangeListener(RealmChangeListener {
            favoriteStoreIds = it.toList().map { i -> i.id }

            updateFavoriteStoreResults()
            favoriteStores.value = favoriteStoreResults.orEmpty()
        })
    }

    private val stores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
            val storeQuery = realm.where(PlexureStore::class.java).sort(PlexureConstants.FIELD_NAME)
            val allStoreResults = storeQuery.findAll()
            it.value = allStoreResults.toList()

            allStoreResults.addChangeListener(RealmChangeListener { newResults ->
                stores.value = newResults.toList()
            })
        }
    }

    private val favoriteStores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
            updateFavoriteStoreResults()
            it.value = favoriteStoreResults.orEmpty()
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

    fun getFavoriteStoreIds(): List<String> {
        return favoriteStoreIds
    }

    private fun updateFavoriteStoreResults() {
        val query = realm.where(PlexureStore::class.java).sort(PlexureConstants.FIELD_NAME)
        val favoriteIdsArray = favoriteStoreIds.toTypedArray()
        favoriteStoreResults = query.`in`(PlexureConstants.FIELD_ID, favoriteIdsArray).findAll()
    }

}