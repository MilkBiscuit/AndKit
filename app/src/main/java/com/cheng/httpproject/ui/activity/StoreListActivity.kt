package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.cheng.httpproject.R
import com.cheng.httpproject.service.PlexureService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.adapter.PlexurePagerAdapter
import com.cheng.httpproject.util.applySchedulers

class StoreListActivity : BaseActivity() {

    val plexureService = PlexureService.getInstance()
    lateinit var sectionsPagerAdapter: PlexurePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        sectionsPagerAdapter = PlexurePagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        loadingView = findViewById(R.id.layout_loading)

        viewPager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(viewPager)
        fetchStores()
    }

    private fun fetchStores() {
        showLoading()
        val observable = plexureService.fetchStores(26.333351598841787, 127.79896146273005)
        val disposable = observable.applySchedulers()
                .subscribe({result ->
                    val fragment = sectionsPagerAdapter.getFragment(0)!!
                    fragment.setStoreData(result)

                    hideLoading()
                }, {error ->
                    hideLoading()
                })
        addDisposable(disposable)
    }


}