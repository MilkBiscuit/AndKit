package com.cheng.httpproject.ui.fragment

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckedTextView
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.ui.activity.StoreListActivity
import com.cheng.httpproject.ui.fragment.base.BaseListDialogFragment
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel

class PlexureFeatureListDialogFragment: BaseListDialogFragment(), AdapterView.OnItemClickListener {

    companion object {
        val TAG = PlexureFeatureListDialogFragment::class.java.simpleName
        val LIST_OF_FEATURES = listOf(
                PlexureConstants.FEATURE_BF,
                PlexureConstants.FEATURE_BP,
                PlexureConstants.FEATURE_DRIVE_THROUGH,
                PlexureConstants.FEATURE_MC_ADVENT,
                PlexureConstants.FEATURE_PLAY_LAND,
                PlexureConstants.FEATURE_TABLE,
                PlexureConstants.FEATURE_WIFI
        )
        val LIST_COUNT = LIST_OF_FEATURES.size
        val SELECTED_LIST = BooleanArray(LIST_COUNT) { i ->
            val feature = LIST_OF_FEATURES[i]
            PlexureStoreViewModel.SELECTED_FEATURE_LIST.contains(feature)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle("Filter results")

        return dialog
    }

    override fun setupCustomView(): View {
        val rootView = super.setupCustomView()
        val adapter = PlexureFeatureAdapter(context!!)
        listView.adapter = adapter
        listView.onItemClickListener = this

        return rootView
    }

    override var positiveAction: (() -> Unit)? = {
        val storeListActivity = activity as StoreListActivity

        storeListActivity.refreshCurrentStoreList()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val checked = !SELECTED_LIST[position]
        val checkedTextView = view!!.findViewById<CheckedTextView>(R.id.check_text_view)
        val feature = LIST_OF_FEATURES[position]

        SELECTED_LIST[position] = checked
        checkedTextView.isChecked = checked
        if (checked) {
            PlexureStoreViewModel.SELECTED_FEATURE_LIST.add(feature)
        } else {
            PlexureStoreViewModel.SELECTED_FEATURE_LIST.remove(feature)
        }
    }
}

class PlexureFeatureAdapter(context: Context): ArrayAdapter<String>(
        context, R.layout.item_text_with_checkbox, R.id.check_text_view,
        PlexureFeatureListDialogFragment.LIST_OF_FEATURES
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = super.getView(position, convertView, parent)
        val checkedTextView = rootView.findViewById<CheckedTextView>(R.id.check_text_view)
        checkedTextView.isChecked = PlexureFeatureListDialogFragment.SELECTED_LIST[position]

        return rootView
    }
}