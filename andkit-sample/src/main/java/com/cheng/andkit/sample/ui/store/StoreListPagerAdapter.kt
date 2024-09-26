package com.cheng.andkit.sample.ui.store

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.cheng.andkit.sample.R


class StoreListPagerAdapter(private val storeListActivity: StoreListActivity, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragments: Array<PlexureStoreListFragment?> = arrayOfNulls(PAGE_NUM)

    override fun getItem(position: Int): Fragment {
        val type =
                if (position == 0) PlexureConstants.StoreType.All
                else PlexureConstants.StoreType.Favorite
        val fragment = PlexureStoreListFragment.newInstance(type)
        fragments[position] = fragment

        return fragment
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val objectItem = super.instantiateItem(container, position)
        if (objectItem is PlexureStoreListFragment) {
            fragments[position] = objectItem
        }

        return objectItem
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)

        fragments[position] = null
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return storeListActivity.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return PAGE_NUM
    }

    companion object {
        private const val PAGE_NUM = 2
        private val TAB_TITLES = arrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

}
