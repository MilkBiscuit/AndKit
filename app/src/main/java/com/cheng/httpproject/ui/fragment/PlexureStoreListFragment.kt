package com.cheng.httpproject.ui.fragment


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cheng.andkit.util.UIUtil
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.activity.StoreListActivity
import com.cheng.httpproject.ui.adapter.PlexureStoreAdapter
import com.cheng.httpproject.ui.fragment.base.CustomListFragment
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel

class PlexureStoreListFragment : CustomListFragment(), SwipeRefreshLayout.OnRefreshListener {

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
        swipeLayout.setOnRefreshListener(this)

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

    override fun onRefresh() {
        activity.fetchStores(PlexureConstants.DEFAULT_LATITUDE, PlexureConstants.DEFAULT_LONGITUDE)
    }

    fun refresh() {
        plexureStoreVM.refreshLiveData()
    }

    fun setIsRefreshing(isRefreshing: Boolean) {
        swipeLayout.isRefreshing = isRefreshing
    }

    private fun setStoreData(items: List<PlexureStore>) {
        refreshEmptyView(items.isEmpty())
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}
