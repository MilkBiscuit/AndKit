package com.cheng.andkit.sample.ui.store

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.sample.domain.model.PlexureStore
import com.cheng.andkit.sample.usecase.FetchStoreDataFromCloudUC
import com.cheng.andkit.sample.usecase.FilterAndSortStoreUC
import com.cheng.andkit.sample.usecase.LocalFileStoreDataUC
import com.cheng.andkit.sample.usecase.PlexureStoreSortMethod
import kotlinx.coroutines.launch


class StoreListViewModel : ViewModel() {
    var sortMethod = PlexureStoreSortMethod.NEAREST
    val selectedFeatures: MutableSet<String> = mutableSetOf()

    private val fetchStoreDataFromCloudUC = FetchStoreDataFromCloudUC()
    private val filterAndSortStoreUC = FilterAndSortStoreUC()
    private val localFileStoreDataUC = LocalFileStoreDataUC()

    private var allStores = localFileStoreDataUC.readAllStores()
    val stores: MutableLiveData<List<PlexureStore>> = MutableLiveData(allStores)
    val favoriteStores: MutableLiveData<List<PlexureStore>> = MutableLiveData(
        localFileStoreDataUC.readFavouriteStores()
    )
    var favoriteStoreIds: List<String> = localFileStoreDataUC.readFavouriteStores().map { it.id!! }
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        Lumberjack.w("StoreListViewModel instance is $this")
    }

    fun isFeatureSelected(feature: String): Boolean = selectedFeatures.contains(feature)

    fun refreshStoreData() {
        viewModelScope.launch {
            isLoading.value = true
            val remoteStoreList = fetchStoreDataFromCloudUC.invoke()
            if (remoteStoreList.isNotEmpty()) {
                allStores = remoteStoreList
                localFileStoreDataUC.writeAllStores(remoteStoreList)
                filterAndSortStoreData()
            }
            isLoading.value = false
        }
    }

    fun filterAndSortStoreData() {
        stores.value = filterAndSortStoreUC.invoke(
            inputList = allStores,
            selectedFeatures = selectedFeatures,
            sortMethod = sortMethod,
        )
    }

    fun toggleFavourite(storeId: String) {
        val currentFavouriteStores = favoriteStores.value!!
        val newFavouriteStores: List<PlexureStore>
        val toggledStore = stores.value!!.find { it.id == storeId }

        if (favoriteStoreIds.contains(storeId)) {
            favoriteStoreIds = favoriteStoreIds.minus(storeId)

            newFavouriteStores = currentFavouriteStores.filterNot { it.id == storeId }
        } else {
            favoriteStoreIds = favoriteStoreIds.plus(storeId)
            newFavouriteStores = currentFavouriteStores.plus(toggledStore!!)
        }
        localFileStoreDataUC.writeFavouriteStores(newFavouriteStores)
        favoriteStores.value = newFavouriteStores
    }

}
