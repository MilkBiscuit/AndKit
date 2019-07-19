package com.cheng.httpproject.ui.fragment.base


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.cheng.httpproject.R
import kotlinx.android.synthetic.main.fragment_custom_list.*
import android.support.v7.widget.DividerItemDecoration

open class CustomListFragment : BaseFragment() {

    override val TAG = "CustomList"
    protected lateinit var rootLayout: View
    protected lateinit var recyclerView: RecyclerView
    protected var tvEmpty: TextView? = null
    protected var btnEmpty: Button? = null
    protected var emptyButtonClick: View.OnClickListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootLayout = custom_list_root
        recyclerView = rv_custom
        tvEmpty = tv_empty_view
        btnEmpty = btn_empty_view

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)

        val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)

        refreshEmptyView(true)
    }

    fun refreshEmptyView(isEmpty: Boolean, emptyText: String? = null, buttonText: String? = null) {
        if (isEmpty) {
            val emptyPrompt = emptyText ?: getString(R.string.no_result)
            showEmptyView(emptyPrompt, buttonText)
        } else {
            hideEmptyView()
        }
    }

    private fun showEmptyView(emptyText: String, buttonText: String?) {
        layout_empty_view?.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE

        tvEmpty?.text = emptyText
        if (buttonText.isNullOrEmpty()) {
            btnEmpty?.visibility = View.GONE
        } else {
            btnEmpty?.visibility = View.VISIBLE
            btnEmpty?.text = buttonText
            btnEmpty?.setOnClickListener(emptyButtonClick)
        }
    }

    private fun hideEmptyView() {
        layout_empty_view?.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }


}
