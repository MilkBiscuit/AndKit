package com.cheng.andkit.sample.ui.activity

import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.cheng.andkit.sample.R

abstract class BaseActivity : AppCompatActivity() {

    protected var loadingView: View? = null;
    protected var toast: Toast? = null
    private var isVisibleToUser = false
    private var isResumed = false

    override fun onStart() {
        super.onStart()

        loadingView = findViewById(R.id.layout_loading)
        isVisibleToUser = true
    }

    override fun onResume() {
        super.onResume()

        isResumed = true
    }

    override fun onPause() {
        super.onPause()

        isResumed = false
    }

    override fun onStop() {
        super.onStop()

        isVisibleToUser = false
    }

    fun replaceFragment(@IdRes id: Int, fragment: Fragment) {
        if (!isVisibleToUser) {
            return
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(id, fragment)
        transaction.commit()
    }

    fun showToast(toastText: String) {
        toast.let {
            if (it == null) {
                toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG).also(Toast::show)
            } else {
                it.setText(toastText)
                it.show()
            }
        }
    }

    open fun showLoading() {
        loadingView?.visibility = View.VISIBLE

//        mIdlingResource?.setIdle(false)
    }

    open fun hideLoading() {
        loadingView?.visibility = View.GONE

//        mIdlingResource?.setIdle(true)
    }

}
