package com.cheng.httpproject.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.model.PlexureFavoriteStoreId
import com.cheng.httpproject.model.PlexureStore
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults

class PlexureStoreViewModel : ViewModel() {

    companion object {
        val SELECTED_FEATURE_LIST: MutableSet<String> = mutableSetOf()
        var sortMethod = PlexureConstants.SortMethod.NEAREST
    }

    private val realm = Realm.getDefaultInstance()
    private var favoriteIdResults = realm.where(PlexureFavoriteStoreId::class.java).findAll()
    private var allStoreResults = realm.where(PlexureStore::class.java).findAll()
    private var favoriteStoreResults: RealmResults<PlexureStore>? = null
    private var favoriteStoreIds: List<String> = favoriteIdResults.toList().map { i -> i.id }

    init {
        allStoreResults.addChangeListener(RealmChangeListener {
            allStoreResults = it
            refreshLiveData()
        })

        favoriteIdResults.addChangeListener(RealmChangeListener {
            favoriteIdResults = it
            favoriteStoreIds = it.toList().map { i -> i.id }

            updateFavoriteStoreResults()
            refreshLiveData()
        })
    }

    private val stores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
            it.value = allStoreResults.toList()
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

    fun refreshLiveData() {
        stores.value = allStoreResults.toList()
        favoriteStores.value = favoriteStoreResults.orEmpty()

        if (SELECTED_FEATURE_LIST.isNotEmpty()) {
            val withFeature: (PlexureStore) -> Boolean = { s ->
                s.featureList?.containsAll(SELECTED_FEATURE_LIST) ?: false
            }
            stores.value = stores.value?.filter(withFeature)
            favoriteStores.value = favoriteStores.value?.filter(withFeature)
        }

        val selectDistance: (PlexureStore) -> Int? = { s -> s.distance }
        when (sortMethod) {
            PlexureConstants.SortMethod.NEAREST -> {
                stores.value = stores.value?.sortedBy(selectDistance)
                favoriteStores.value = favoriteStores.value?.sortedBy(selectDistance)
            }
            PlexureConstants.SortMethod.FURTHERMOST -> {
                stores.value = stores.value?.sortedByDescending(selectDistance)
                favoriteStores.value = favoriteStores.value?.sortedByDescending(selectDistance)
            }
            else -> {
                val selectName: (PlexureStore) -> String? = { s -> s.name }
                stores.value = stores.value?.sortedBy(selectName)
                favoriteStores.value = favoriteStores.value?.sortedBy(selectName)
            }
        }
    }

    private fun updateFavoriteStoreResults() {
        val query = realm.where(PlexureStore::class.java)
        val favoriteIdsArray = favoriteStoreIds.toTypedArray()
        favoriteStoreResults = query.`in`(PlexureConstants.FIELD_ID, favoriteIdsArray).findAll()
    }

}