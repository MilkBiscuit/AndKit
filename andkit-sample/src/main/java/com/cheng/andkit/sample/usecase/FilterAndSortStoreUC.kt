package com.cheng.andkit.sample.usecase

import com.cheng.andkit.sample.domain.model.PlexureStore

class FilterAndSortStoreUC {

    fun invoke(
        inputList: List<PlexureStore>,
        selectedFeatures: Collection<String>,
        sortMethod: PlexureStoreSortMethod,
    ): List<PlexureStore> {
        var outputList = inputList
        if (selectedFeatures.isNotEmpty()) {
            val withFeature: (PlexureStore) -> Boolean = { s ->
                s.featureList?.containsAll(selectedFeatures) ?: false
            }
            outputList = outputList.filter(withFeature)
        }
        val selectDistance: (PlexureStore) -> Int? = { s -> s.distance }
        when (sortMethod) {
            PlexureStoreSortMethod.NEAREST -> {
                outputList = outputList.sortedBy(selectDistance)
            }
            PlexureStoreSortMethod.FURTHERMOST -> {
                outputList = outputList.sortedByDescending(selectDistance)
            }
            else -> {
                val selectName: (PlexureStore) -> String? = { s -> s.name }
                outputList = outputList.sortedBy(selectName)
            }
        }

        return outputList
    }

}
