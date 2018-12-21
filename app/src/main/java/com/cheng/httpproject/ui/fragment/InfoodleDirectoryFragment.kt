package com.cheng.httpproject.ui.fragment

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.httpproject.R
import com.cheng.httpproject.applySchedulers
import com.cheng.httpproject.helper.SharedPrefHelper
import com.cheng.httpproject.service.InfoodleApiService
import com.cheng.httpproject.ui.activity.InfoodleActivity
import com.cheng.httpproject.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_infoodle_directory.*

class InfoodleDirectoryFragment : BaseFragment(), SearchView.OnQueryTextListener {

    override val TAG = "InfoodleDirectory"
    private lateinit var activity: InfoodleActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_infoodle_directory, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as InfoodleActivity
        sv_infoodle_directory.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        search(query)

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        search(newText)

        return true
    }

    fun search(keyword: String?) {
        if (keyword == null || keyword.length <= 2) {
            return
        }

        val observable = InfoodleApiService.getInstance(activity).getService().searchPerson(keyword)
        val disposable = observable.applySchedulers().subscribe({result ->
            if (result.people != null) {
                val fragment = InfoodleContactListFragment.newInstance(result.people)

                replaceFragment(R.id.layout_infoodle_contact_list, fragment)
            }
        }, {error ->
            activity.showToast(error.localizedMessage)
        })

        activity.addDisposable(disposable)
    }

}
