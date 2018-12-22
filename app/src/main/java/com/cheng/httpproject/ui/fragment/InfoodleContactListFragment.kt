package com.cheng.httpproject.ui.fragment


import android.os.Bundle
import com.cheng.httpproject.R
import com.cheng.httpproject.model.InfoodleSearchPersonItem
import com.cheng.httpproject.ui.activity.InfoodleActivity
import com.cheng.httpproject.ui.adapter.InfoodleContactAdapter
import com.cheng.httpproject.ui.fragment.base.CustomListFragment
import com.cheng.httpproject.util.UIUtil

class InfoodleContactListFragment : CustomListFragment() {

    private lateinit var activity: InfoodleActivity
    private lateinit var adapter: InfoodleContactAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as InfoodleActivity
        adapter = InfoodleContactAdapter(activity, emptyList())
        UIUtil.applyRoundCorner(activity, rootLayout, R.color.transparent_10_black)
        recyclerView.adapter = adapter
    }

    fun setPeopleData(items: List<InfoodleSearchPersonItem>) {
        refreshEmptyView(items.isEmpty())
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

}
