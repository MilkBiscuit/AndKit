package com.cheng.httpproject.ui

import android.support.v7.app.AppCompatActivity
import android.view.View

abstract class BaseActivity : AppCompatActivity() {

    protected var loadingView: View? = null;

    protected fun showLoading() {
        loadingView?.visibility = View.VISIBLE
    }

    protected fun hideLoading() {
        loadingView?.visibility = View.GONE
    }

}