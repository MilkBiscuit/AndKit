package com.cheng.httpproject.ui.fragment.base


import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.cheng.httpproject.databinding.LoadingBinding

abstract class BaseFragment : Fragment() {

    abstract val TAG: String
    protected var loadingView: View? = null
    private lateinit var binding: LoadingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingView = binding.layoutLoading
    }

    fun replaceFragment(@IdRes id: Int, fragment: Fragment) {
        if (!isAdded) {
            return
        }
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(id, fragment)
        transaction?.commit()
    }

    fun showLoading() {
        if (isAdded) {
            loadingView?.visibility = View.VISIBLE
        }
    }

    fun hideLoading() {
        if (isAdded) {
            loadingView?.visibility = View.GONE
        }
    }

    protected fun showDialog(message: String, title: String? = null) {
        val dialog = BaseDialogFragment()
        dialog.title = title
        dialog.message = message

        fragmentManager?.let {
            dialog.show(it, BaseDialogFragment.TAG)
        }
    }

}
