package com.cheng.httpproject.ui.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.activity.StoreListActivity
import com.cheng.httpproject.ui.adapter.PlexureStoreAdapter
import com.cheng.httpproject.ui.fragment.base.CustomListFragment
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel
import com.cheng.httpproject.util.UIUtil

class PlexureStoreListFragment : CustomListFragment() {

    companion object {
        private val KEY_STORE_TYPE = "StoreType"

        // 0 -> default, all store
        // 1 -> favorite store
        fun newInstance(type: PlexureConstants.StoreType) : PlexureStoreListFragment {
            val fragment = PlexureStoreListFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_STORE_TYPE, type.value)
                }
            }

            return fragment
        }
    }

    private lateinit var activity: StoreListActivity
    private lateinit var adapter: PlexureStoreAdapter
    private lateinit var plexureStoreVM: PlexureStoreViewModel
    private var storeType = PlexureConstants.StoreType.All

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(KEY_STORE_TYPE)) {
                storeType = PlexureConstants.StoreType.valueOf(it.getInt(KEY_STORE_TYPE))!!
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as StoreListActivity
        adapter = PlexureStoreAdapter(activity, emptyList())
        UIUtil.applyRoundCorner(activity, rootLayout, R.color.transparent_10_black)
        recyclerView.adapter = adapter

        plexureStoreVM = ViewModelProviders.of(this).get(PlexureStoreViewModel::class.java)
        val storeListLiveData =
                if (storeType == PlexureConstants.StoreType.All) plexureStoreVM.getAllStores()
                else plexureStoreVM.getFavoriteStores()
        storeListLiveData.observe(this, Observer<List<PlexureStore>>{ stores ->
            if (stores != null) {
                setStoreData(stores)
            }
        })
    }

    fun refresh() {
        adapter.notifyDataSetChanged()
    }

    fun sortByNearest() {
        plexureStoreVM.sortByNearest()
    }

    fun sortByFurtherMost() {
        plexureStoreVM.sortByFurtherMost()
    }

    private fun setStoreData(items: List<PlexureStore>) {
        refreshEmptyView(items.isEmpty())
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}
