package com.cheng.andkit.sample.ui.store

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.ui.activity.BaseActivity
import com.cheng.andkit.sample.ui.store.featuredialog.PlexureFeatureListDialogFragment
import com.cheng.andkit.sample.usecase.PlexureStoreSortMethod

class StoreListActivity : BaseActivity() {

    private val currentFragment: PlexureStoreListFragment?
        get() = sectionsPagerAdapter.fragments[currentPageIndex]

    private lateinit var sectionsPagerAdapter: StoreListPagerAdapter
    private var currentPageIndex: Int = 0

    private val storeListViewModel: StoreListViewModel by viewModels()

    private val pageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(i: Int, v: Float, i2: Int) {}

        override fun onPageSelected(i: Int) {
            currentPageIndex = i
        }

        override fun onPageScrollStateChanged(i: Int) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_store_list)
        val viewPager: ViewPager = findViewById(R.id.view_pager)

        sectionsPagerAdapter = StoreListPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = sectionsPagerAdapter
        viewPager.addOnPageChangeListener(pageChangeListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.store_list, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val storeListFragment = currentFragment ?: return false
        when (item.itemId) {
            R.id.sort_far -> storeListViewModel.sortMethod = PlexureStoreSortMethod.FURTHERMOST
            R.id.sort_near -> storeListViewModel.sortMethod = PlexureStoreSortMethod.NEAREST
            R.id.sort_name -> storeListViewModel.sortMethod = PlexureStoreSortMethod.NAME
            R.id.action_filter -> {
                val dialog = PlexureFeatureListDialogFragment(storeListViewModel)
                dialog.show(supportFragmentManager, "PlexureFeatureListDialog")
            }
        }
        storeListFragment.refresh()

        return true
    }

    override fun showLoading() {
        currentFragment?.setIsRefreshing(true)
    }

    override fun hideLoading() {
        currentFragment?.setIsRefreshing(false)
    }
}
