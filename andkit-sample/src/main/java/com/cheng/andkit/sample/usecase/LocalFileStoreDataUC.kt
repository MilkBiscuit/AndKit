package com.cheng.andkit.sample.usecase

import com.cheng.andkit.sample.domain.model.PlexureStore
import com.cheng.andkit.util.FileHelper
import com.cheng.andkit.util.JsonUtil
import com.cheng.andkit.util.android.ContextHolder
import java.io.File

class LocalFileStoreDataUC {

    private val appContext = ContextHolder.getAppContext()
    private val allStoresFile = File(appContext.filesDir, "allStores")
    private val favouriteStoresFile = File(appContext.filesDir, "favouriteStores")

    fun readAllStores(): List<PlexureStore> = readStores(allStoresFile)

    fun readFavouriteStores(): List<PlexureStore> = readStores(favouriteStoresFile)

    fun writeAllStores(storeList: List<PlexureStore>) = writeStoresToFile(allStoresFile, storeList)

    fun writeFavouriteStores(storeList: List<PlexureStore>) = writeStoresToFile(favouriteStoresFile, storeList)

    private fun readStores(file: File): List<PlexureStore> {
        val jsonString = FileHelper.readFileToString(file)
        return JsonUtil.jsonToObject<List<PlexureStore>>(jsonString) ?: emptyList()
    }

    private fun writeStoresToFile(file: File, storeList: List<PlexureStore>): Boolean {
        val jsonString = JsonUtil.objectToJson(storeList)
        return FileHelper.atomicWriteToFile(file, jsonString)
    }

}
