package com.cheng.httpproject.ui.fragment


import android.os.Bundle
import com.cheng.httpproject.R
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.activity.StoreListActivity
import com.cheng.httpproject.ui.adapter.PlexureStoreAdapter
import com.cheng.httpproject.ui.fragment.base.CustomListFragment
import com.cheng.httpproject.util.UIUtil

class PlexureStoreListFragment : CustomListFragment() {

    private lateinit var activity: StoreListActivity
    private lateinit var adapter: PlexureStoreAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as StoreListActivity
        adapter = PlexureStoreAdapter(activity, emptyList())
        UIUtil.applyRoundCorner(activity, rootLayout, R.color.transparent_10_black)
        recyclerView.adapter = adapter
    }

    fun setStoreData(items: List<PlexureStore>) {
        refreshEmptyView(items.isEmpty())
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}
