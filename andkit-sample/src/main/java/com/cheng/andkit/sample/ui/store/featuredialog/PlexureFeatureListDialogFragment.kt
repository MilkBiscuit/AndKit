package com.cheng.andkit.sample.ui.store.featuredialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.domain.model.PlexureStoreFeature
import com.cheng.andkit.sample.ui.store.StoreListViewModel


class PlexureFeatureListDialogFragment(
    private val storeListViewModel: StoreListViewModel,
): DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
            .setTitle(R.string.filter_condition)
            .setPositiveButton("OK") { _, _ ->
                storeListViewModel.filterAndSortStoreData()
            }
            .setNegativeButton("Cancel") { _, _ ->
            }
        val rootView = requireActivity().layoutInflater.inflate(R.layout.dialog_list, null)
        setupCustomView(rootView)
        builder.setView(rootView)

        return builder.create()
    }

    private fun setupCustomView(rootView: View): View {
        val listView = rootView.findViewById<ListView>(R.id.list)
        val adapter = PlexureFeatureAdapter(requireContext(), storeListViewModel)
        listView.adapter = adapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val feature = PlexureStoreFeature.LIST_OF_FEATURES[position]
            val currentlySelected = storeListViewModel.isFeatureSelected(feature)
            if (currentlySelected) {
                storeListViewModel.selectedFeatures.remove(feature)
            } else {
                storeListViewModel.selectedFeatures.add(feature)
            }
            adapter.notifyDataSetChanged()
        }

        return rootView
    }

}
