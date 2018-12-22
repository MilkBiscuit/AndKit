package com.cheng.httpproject.ui.fragment.base


import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.loading.*

abstract class BaseFragment : Fragment() {

    abstract val TAG: String
    protected var loadingView: View? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingView = layout_loading
    }

    fun replaceFragment(@IdRes id: Int, fragment: Fragment) {
        if (!isAdded) {
            return
        }
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(id, fragment)
        transaction?.commit()
    }

    protected fun showLoading() {
        if (isAdded) {
            loadingView?.visibility = View.VISIBLE
        }
    }

    protected fun hideLoading() {
        if (isAdded) {
            loadingView?.visibility = View.GONE
        }
    }

    protected fun showDialog(message: String, title: String? = null) {
        val dialog = BaseDialogFragment()
        dialog.title = title
        dialog.message = message

        dialog.show(fragmentManager, dialog.TAG)
    }

}
