package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.cheng.httpproject.R
import com.cheng.httpproject.helper.PlexureStoreDao
import com.cheng.httpproject.service.PlexureService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.adapter.PlexurePagerAdapter
import com.cheng.httpproject.util.applySchedulers

class StoreListActivity : BaseActivity() {

    private val plexureService = PlexureService.getInstance()
    private lateinit var sectionsPagerAdapter: PlexurePagerAdapter

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i2: Int) {}

        override fun onPageSelected(i: Int) {
            sectionsPagerAdapter.fragments[i]?.refresh()
        }

        override fun onPageScrollStateChanged(i: Int) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_store_list)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val tabs: TabLayout = findViewById(R.id.tabs)
        loadingView = findViewById(R.id.layout_loading)

        sectionsPagerAdapter = PlexurePagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(pageChangeListener)
        tabs.setupWithViewPager(viewPager)
        fetchStores()
    }

    private fun fetchStores() {
        showLoading()
        val observable = plexureService.fetchStores(26.333351598841787, 127.79896146273005)
        val disposable = observable.applySchedulers()
                .subscribe({result ->
                    val plexureStoreDao = PlexureStoreDao.getInstance(this)
                    plexureStoreDao.addOrUpdate(result)

                    hideLoading()
                }, {error ->
                    hideLoading()
                })
        addDisposable(disposable)
    }


}