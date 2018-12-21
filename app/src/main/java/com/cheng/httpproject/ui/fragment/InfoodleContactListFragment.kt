package com.cheng.httpproject.ui.fragment


import android.os.Bundle
import com.cheng.httpproject.model.InfoodleSearchPersonItem
import com.cheng.httpproject.ui.activity.InfoodleActivity
import com.cheng.httpproject.ui.adapter.InfoodleDirectoryPersonAdapter
import com.cheng.httpproject.ui.fragment.base.CustomListFragment

private const val ARG_PERSON_LIST = "personList"

class InfoodleContactListFragment : CustomListFragment() {

    companion object {
        @JvmStatic
        fun newInstance(items: List<InfoodleSearchPersonItem>) = InfoodleContactListFragment()
                .apply {
                    arguments = Bundle().apply {
                        val arrayList = ArrayList<InfoodleSearchPersonItem>(items)
                        putParcelableArrayList(ARG_PERSON_LIST, arrayList)
                    }
                }
    }

    private var personList: List<InfoodleSearchPersonItem>? = null
    private lateinit var activity: InfoodleActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            personList = it.getParcelableArrayList(ARG_PERSON_LIST)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as InfoodleActivity
        recyclerView.adapter = InfoodleDirectoryPersonAdapter(activity, personList!!)
    }

}
