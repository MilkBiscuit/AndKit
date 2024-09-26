package com.cheng.andkit.sample.ui.store

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import androidx.fragment.app.DialogFragment
import com.cheng.andkit.sample.R


class PlexureFeatureListDialogFragment(
    private val storeListViewModel: StoreListViewModel,
): DialogFragment(), AdapterView.OnItemClickListener {

    companion object {
        val TAG: String = PlexureFeatureListDialogFragment::class.java.simpleName
        private val LIST_OF_FEATURES = listOf(
            PlexureConstants.FEATURE_BF,
            PlexureConstants.FEATURE_BP,
            PlexureConstants.FEATURE_DRIVE_THROUGH,
            PlexureConstants.FEATURE_MC_ADVENT,
            PlexureConstants.FEATURE_PLAY_LAND,
            PlexureConstants.FEATURE_TABLE,
            PlexureConstants.FEATURE_WIFI
        )
        private val LIST_COUNT = LIST_OF_FEATURES.size
    }

//    private val selectedFeatures = BooleanArray(LIST_COUNT) { i ->
//        val feature = LIST_OF_FEATURES[i]
//        storeListViewModel.selectedFeatureList.contains(feature)
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle(R.string.filter_condition)

        return dialog
    }

//    override fun setupCustomView(): View {
//        val rootView = super.setupCustomView()
//        val adapter = PlexureFeatureAdapter(context!!)
//        listView.adapter = adapter
//        listView.onItemClickListener = this
//
//        return rootView
//    }
//
//    override var positiveAction: (() -> Unit)? = {
//        val storeListActivity = activity as StoreListActivity
//
//        storeListActivity.refreshCurrentStoreList()
//    }

    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        val checked = isChecked(position)
        val checkedTextView = view.findViewById<CheckedTextView>(R.id.check_text_view)
        val feature = LIST_OF_FEATURES[position]

//        selectedFeatures[position] = checked
        checkedTextView.isChecked = checked
        if (checked) {
            storeListViewModel.selectedFeatureList.add(feature)
        } else {
            storeListViewModel.selectedFeatureList.remove(feature)
        }
    }

    private fun isChecked(position: Int): Boolean {
        val feature = LIST_OF_FEATURES[position]
        return storeListViewModel.selectedFeatureList.contains(feature)
    }
}

//class PlexureFeatureAdapter(context: Context): ArrayAdapter<String>(
//        context, R.layout.item_text_with_checkbox, R.id.check_text_view,
//        PlexureFeatureListDialogFragment.LIST_OF_FEATURES
//) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val rootView = super.getView(position, convertView, parent)
//        val checkedTextView = rootView.findViewById<CheckedTextView>(R.id.check_text_view)
//        checkedTextView.isChecked = PlexureFeatureListDialogFragment.SELECTED_LIST[position]
//
//        return rootView
//    }
//}
