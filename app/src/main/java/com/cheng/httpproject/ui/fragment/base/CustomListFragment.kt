package com.cheng.httpproject.ui.fragment.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cheng.httpproject.R
import com.cheng.httpproject.databinding.FragmentCustomListBinding

open class CustomListFragment : BaseFragment() {

    override val TAG = "CustomList"
    protected lateinit var rootLayout: View
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var tvEmpty: TextView
    protected lateinit var btnEmpty: Button
    protected lateinit var swipeLayout: SwipeRefreshLayout
    protected lateinit var emptyButtonClick: View.OnClickListener
    private lateinit var binding: FragmentCustomListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_custom_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootLayout = binding.customListRoot
        swipeLayout = binding.swipeRefreshLayout
        recyclerView = binding.rvCustom
        tvEmpty = binding.tvEmptyView
        btnEmpty = binding.btnEmptyView

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
        binding.layoutEmptyView.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE

        tvEmpty.text = emptyText
        if (buttonText.isNullOrEmpty()) {
            btnEmpty.visibility = View.GONE
        } else {
            btnEmpty.visibility = View.VISIBLE
            btnEmpty.text = buttonText
            btnEmpty.setOnClickListener(emptyButtonClick)
        }
    }

    private fun hideEmptyView() {
        binding.layoutEmptyView.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }


}
