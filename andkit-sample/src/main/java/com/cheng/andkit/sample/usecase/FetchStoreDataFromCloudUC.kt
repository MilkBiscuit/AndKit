package com.cheng.andkit.sample.usecase

import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.network.RestApiHelper
import com.cheng.andkit.sample.domain.model.PlexureStore
import com.cheng.andkit.util.JsonUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchStoreDataFromCloudUC {

    suspend operator fun invoke(): List<PlexureStore> {
        return withContext(Dispatchers.IO) {
            val url = "https://mopjapaneastgateway.plexure.io/store/v2/stores?radius=100000000&size=100"
            val result = RestApiHelper.getApi(url, emptyMap())
            if (result.isSuccess) {
                val jsonString = result.getOrElse { "" }
                val parsedList = JsonUtil.jsonToObject<List<PlexureStore>>(jsonString)

                return@withContext parsedList ?: emptyList()
            }
            Lumberjack.w("FetchStoreData failed: ${result.exceptionOrNull()}")

            return@withContext emptyList()
        }
    }

}
