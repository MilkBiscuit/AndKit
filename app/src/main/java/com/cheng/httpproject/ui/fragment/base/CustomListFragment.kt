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

        recyclerView = rv_custom
        tvEmpty = tv_empty_view
        btnEmpty = btn_empty_view

        val layoutManager = LinearLayoutManager(activity)
        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setHasFixedSize(true)

        val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    fun setupEmptyView(emptyText: String, buttonText: String) {
        layout_empty_view.visibility = View.VISIBLE

        tvEmpty?.text = emptyText
        btnEmpty?.text = buttonText
        btnEmpty?.setOnClickListener(emptyButtonClick)
    }


}
