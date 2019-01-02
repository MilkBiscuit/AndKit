package com.cheng.httpproject.ui.fragment

import android.os.Bundle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.httpproject.R
import com.cheng.httpproject.util.*
import com.cheng.httpproject.service.InfoodleApiService
import com.cheng.httpproject.ui.activity.InfoodleActivity
import com.cheng.httpproject.ui.fragment.base.BaseFragment
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.fragment_infoodle_directory.*

class InfoodleDirectoryFragment : BaseFragment(), SearchView.OnQueryTextListener {

    override val TAG = "InfoodleDirectory"
    private lateinit var activity: InfoodleActivity
    private lateinit var listFragment: InfoodleContactListFragment
    private val userInputSubject = BehaviorSubject.create<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_infoodle_directory, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity = getActivity() as InfoodleActivity
        sv_infoodle_directory.setOnQueryTextListener(this)

        listFragment = InfoodleContactListFragment()
        replaceFragment(R.id.layout_infoodle_contact_list, listFragment)

        startObserve()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        userInputSubject.onNext(query ?: "")

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        userInputSubject.onNext(newText ?: "")

        return true
    }

    fun startObserve() {
        val subject = userInputSubject.filter { it.length >= 2 }.debounceOneSecond()
        var disposable = subject.applySchedulers().doOnNext{listFragment.showLoading()}.subscribe()
        activity.addDisposable(disposable)

        disposable = subject.flatMap {
            val infoodleService = InfoodleApiService.getInstance(activity).getService()
            infoodleService.searchPerson(it).applySchedulers().doOnError{error ->
                listFragment.hideLoading()
                Log.w(TAG, "search failed: $error")
            }
        }.applySchedulers().subscribe{result ->
            listFragment.hideLoading()
            listFragment.setPeopleData(result.people?: emptyList())
        }
        activity.addDisposable(disposable)
    }

}
