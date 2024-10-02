package com.cheng.andkit.sample.ui.store.featuredialog

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.domain.model.PlexureStoreFeature
import com.cheng.andkit.sample.ui.store.StoreListViewModel

class PlexureFeatureAdapter(
    context: Context,
    private val storeListViewModel: StoreListViewModel,
): ArrayAdapter<String>(
    context,
    R.layout.item_text_with_checkbox,
    R.id.check_text_view,
    PlexureStoreFeature.LIST_OF_FEATURES,
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = super.getView(position, convertView, parent)
        val checkedTextView = rootView.findViewById<CheckedTextView>(R.id.check_text_view)
        checkedTextView.isChecked = storeListViewModel.isFeatureSelected(
            PlexureStoreFeature.LIST_OF_FEATURES[position]
        )

        return rootView
    }
}
