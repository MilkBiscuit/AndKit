package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.cheng.httpproject.R
import com.cheng.httpproject.helper.PlexureStoreDao
import com.cheng.httpproject.service.PlexureService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.adapter.PlexurePagerAdapter
import com.cheng.httpproject.ui.fragment.PlexureStoreListFragment
import com.cheng.httpproject.util.applySchedulers

class StoreListActivity : BaseActivity() {

    private val plexureService = PlexureService.getInstance()
    private val currentFragment: PlexureStoreListFragment?
        get() = sectionsPagerAdapter.fragments[currentPageIndex]

    private lateinit var sectionsPagerAdapter: PlexurePagerAdapter
    private var currentPageIndex: Int = 0

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i2: Int) {}

        override fun onPageSelected(i: Int) {
            currentPageIndex = i
            currentFragment?.refresh()
        }

        override fun onPageScrollStateChanged(i: Int) {
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_store_list)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        loadingView = findViewById(R.id.layout_loading)

        sectionsPagerAdapter = PlexurePagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(pageChangeListener)
        fetchStores()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.store_list, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val storeListFragment = currentFragment ?: return false
        when (item.itemId) {
            R.id.sort_far -> storeListFragment.sortByFurtherMost()
            R.id.sort_near -> storeListFragment.sortByNearest()
        }

        return true
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