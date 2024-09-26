package com.cheng.andkit.sample.ui.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheng.andkit.sample.domain.model.PlexureStore
import com.cheng.andkit.sample.usecase.FetchStoreDataUC
import kotlinx.coroutines.launch


class StoreListViewModel : ViewModel() {

    var sortMethod = PlexureConstants.SortMethod.NEAREST
    val selectedFeatureList: MutableSet<String> = mutableSetOf()
//    private val realm = Realm.getDefaultInstance()
//    private var favoriteIdResults = realm.where(PlexureFavoriteStoreId::class.java).findAll()
//    private var allStoreResults = realm.where(PlexureStore::class.java).findAll()
//    private var favoriteStoreResults: RealmResults<PlexureStore>? = null
//    private var favoriteStoreIds: List<String> = favoriteIdResults.toList().map { i -> i.id }
    private val fetchStoreDataUC = FetchStoreDataUC()

    init {
        refreshStoreData()
//        allStoreResults.addChangeListener(RealmChangeListener {
//            allStoreResults = it
//            refreshStoreData()
//        })
//
//        favoriteIdResults.addChangeListener(RealmChangeListener {
//            favoriteIdResults = it
//            favoriteStoreIds = it.toList().map { i -> i.id }
//
//            updateFavoriteStoreResults()
//            refreshStoreData()
//        })
    }

    val stores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
//            it.value = allStoreResults.toList()
        }
    }

    val favoriteStores: MutableLiveData<List<PlexureStore>> by lazy {
        MutableLiveData<List<PlexureStore>>().also {
            updateFavoriteStoreResults()
//            it.value = favoriteStoreResults.orEmpty()
        }
    }

    override fun onCleared() {
        super.onCleared()

//        realm.close()
    }


    fun refreshStoreData() {
//        stores.value = allStoreResults.toList()
//        favoriteStores.value = favoriteStoreResults.orEmpty()

        viewModelScope.launch {
            stores.value = fetchStoreDataUC.invoke()

            if (selectedFeatureList.isNotEmpty()) {
                val withFeature: (PlexureStore) -> Boolean = { s ->
                    s.featureList?.containsAll(selectedFeatureList) ?: false
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
    }

    private fun updateFavoriteStoreResults() {
//        val query = realm.where(PlexureStore::class.java)
//        val favoriteIdsArray = favoriteStoreIds.toTypedArray()
//        favoriteStoreResults = query.`in`(PlexureConstants.FIELD_ID, favoriteIdsArray).findAll()
    }

}
