package com.cheng.httpproject.ui.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.constant.PlexureConstants.DEFAULT_LATITUDE
import com.cheng.httpproject.constant.PlexureConstants.DEFAULT_LONGITUDE
import com.cheng.httpproject.service.PlexureService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.adapter.PlexurePagerAdapter
import com.cheng.httpproject.ui.fragment.PlexureFeatureListDialogFragment
import com.cheng.httpproject.ui.fragment.PlexureStoreListFragment
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel
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

        sectionsPagerAdapter = PlexurePagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(pageChangeListener)
        fetchStores(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.store_list, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val storeListFragment = currentFragment ?: return false
        when (item.itemId) {
            R.id.sort_far -> PlexureStoreViewModel.sortMethod = PlexureConstants.SortMethod.FURTHERMOST
            R.id.sort_near -> PlexureStoreViewModel.sortMethod = PlexureConstants.SortMethod.NEAREST
            R.id.sort_name -> PlexureStoreViewModel.sortMethod = PlexureConstants.SortMethod.NAME
            R.id.action_filter -> {
                val dialog = PlexureFeatureListDialogFragment()
                dialog.show(supportFragmentManager, PlexureFeatureListDialogFragment.TAG)
            }
        }
        storeListFragment.refresh()

        return true
    }

    override fun hideLoading() {
        super.hideLoading()

        currentFragment?.setIsRefreshing(false)
    }

    fun refreshCurrentStoreList() {
        currentFragment?.refresh()
    }

    fun fetchStores(latitude: Double, longitude: Double) {
        showLoading()
        val observable = plexureService.fetchStores(latitude, longitude)
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