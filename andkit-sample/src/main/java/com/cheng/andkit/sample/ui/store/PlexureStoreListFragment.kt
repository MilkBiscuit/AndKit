package com.cheng.andkit.sample.ui.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.databinding.FragmentPlexureListBinding
import com.cheng.andkit.sample.domain.model.PlexureStore
import com.cheng.andkit.sample.ui.fragment.base.BaseFragment
import com.cheng.andkit.util.android.UIUtil


class PlexureStoreListFragment : BaseFragment() {

    override val TAG: String = "PlexureStoreListFragment"

    private lateinit var storeListActivity: StoreListActivity
    private lateinit var adapter: PlexureStoreAdapter
    private val storeListVM: StoreListViewModel by activityViewModels<StoreListViewModel>()
    private var storeType = StoreType.All
    private lateinit var rootLayout: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var btnEmpty: Button
    private lateinit var swipeLayout: SwipeRefreshLayout
    private lateinit var emptyButtonClick: View.OnClickListener
    private lateinit var binding: FragmentPlexureListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(KEY_STORE_TYPE)) {
                storeType = StoreType.valueOf(it.getInt(KEY_STORE_TYPE))!!
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlexureListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootLayout = binding.customListRoot
        swipeLayout = binding.swipeRefreshLayout
        recyclerView = binding.rvCustom
        tvEmpty = binding.tvEmptyView
        btnEmpty = binding.btnEmptyView

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        swipeLayout.setOnRefreshListener {
            refresh()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        storeListActivity = activity as StoreListActivity
        adapter = PlexureStoreAdapter(storeListActivity, storeListVM)
        recyclerView.adapter = adapter
        if (storeType == StoreType.All) {
            storeListVM.stores.observe(viewLifecycleOwner) { stores ->
                setStoreData(stores)
            }
            storeListVM.favoriteStores.observe(viewLifecycleOwner) {
                adapter.notifyDataSetChanged()
            }
        } else {
            storeListVM.favoriteStores.observe(viewLifecycleOwner) { favouriteStores ->
                setStoreData(favouriteStores)
            }
        }
        storeListVM.isLoading.observe(viewLifecycleOwner) { isLoading ->
            swipeLayout.isRefreshing = isLoading
        }

        UIUtil.applyRoundCorner(storeListActivity, rootLayout, R.color.transparent_10_black)
    }

    fun refresh() {
        storeListVM.refreshStoreData()
    }

    fun setIsRefreshing(isRefreshing: Boolean) {
        swipeLayout.isRefreshing = isRefreshing
    }

    private fun setStoreData(items: List<PlexureStore>) {
        adapter.items = items
    }

    companion object {
        private const val KEY_STORE_TYPE = "StoreType"

        // 0 -> default, all stores
        // 1 -> favorite stores
        fun newInstance(type: StoreType) : PlexureStoreListFragment {
            val fragment = PlexureStoreListFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_STORE_TYPE, type.value)
                }
            }

            return fragment
        }
    }

}
