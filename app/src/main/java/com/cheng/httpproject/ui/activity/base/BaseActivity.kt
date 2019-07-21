package com.cheng.httpproject.ui.activity.base

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    protected var active = false
    protected var loadingView: View? = null;
    protected var compositeDisposable = CompositeDisposable()
    protected var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        active = true
    }

    override fun onStop() {
        super.onStop()

        active = false
    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.dispose()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun replaceFragment(@IdRes id: Int, fragment: Fragment) {
        if (!active) {
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

    protected open fun showLoading() {
        loadingView?.visibility = View.VISIBLE
    }

    protected open fun hideLoading() {
        loadingView?.visibility = View.GONE
    }

}