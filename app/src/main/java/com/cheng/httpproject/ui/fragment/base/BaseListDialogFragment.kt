package com.cheng.httpproject.ui.fragment.base

import android.view.View
import android.widget.ListView
import com.cheng.httpproject.R

open class BaseListDialogFragment: BaseDialogFragment() {

    companion object {
        val TAG = BaseListDialogFragment::class.java.simpleName
    }

    protected lateinit var listView: ListView

    override fun getLayoutResId(): Int {
        return R.layout.dialog_list
    }

    override fun setupCustomView(): View {
        val rootView = super.setupCustomView()
        listView = rootView.findViewById(R.id.list)

        return rootView
    }
}