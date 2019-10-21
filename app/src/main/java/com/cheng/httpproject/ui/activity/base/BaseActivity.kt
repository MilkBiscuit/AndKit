package com.cheng.httpproject.ui.activity.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.test.espresso.IdlingResource
import com.cheng.httpproject.SimpleIdlingResource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    // The Idling Resource which will be null in production.
    @Nullable
    private var mIdlingResource: SimpleIdlingResource? = null

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

        mIdlingResource?.setIdle(false)
    }

    protected open fun hideLoading() {
        loadingView?.visibility = View.GONE

        mIdlingResource?.setIdle(true)
    }

    /**
     * Only called from test, creates and returns a new [SimpleIdlingResource].
     */
    @VisibleForTesting
    @NonNull
    fun getIdlingResource(): IdlingResource {
        if (mIdlingResource == null) {
            mIdlingResource = SimpleIdlingResource()
        }

        return mIdlingResource!!
    }

}