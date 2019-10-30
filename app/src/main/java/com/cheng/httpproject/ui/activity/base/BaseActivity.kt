package com.cheng.httpproject.ui.activity.base

import android.content.Context
import android.content.SharedPreferences
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
import com.cheng.httpproject.R
import com.cheng.httpproject.SimpleIdlingResource
import com.cheng.httpproject.constant.PrefConstants
import com.cheng.httpproject.helper.SharedPrefHelper
import com.cheng.httpproject.util.ContextUtil
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    // The Idling Resource which will be null in production.
    @Nullable
    private var mIdlingResource: SimpleIdlingResource? = null

    protected var active = false
    protected var loadingView: View? = null;
    protected var compositeDisposable = CompositeDisposable()
    protected var toast: Toast? = null
    protected lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPrefs = SharedPrefHelper.getInstance(this).sharedPrefs
        sharedPrefs.registerOnSharedPreferenceChangeListener(this)
        ContextUtil.updateTheme(this)
        ContextUtil.updateLocale(this)
        active = true
    }

    override fun onStart() {
        super.onStart()

        loadingView = findViewById(R.id.layout_loading)
    }

    override fun onStop() {
        super.onStop()

        active = false
    }

    override fun onDestroy() {
        super.onDestroy()

        sharedPrefs.unregisterOnSharedPreferenceChangeListener(this)
        compositeDisposable.dispose()
    }

//    override fun attachBaseContext(base: Context) {
//        val newContext = ContextUtil.createConfigurationContext(base)
//        super.attachBaseContext(newContext)
//    }

    override fun onSharedPreferenceChanged(sharedPref: SharedPreferences, key: String) {
        if (key == PrefConstants.PREF_KEY_LANGUAGE || key == PrefConstants.PREF_KEY_THEME) {
            recreate()
        }
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

    open fun showLoading() {
        loadingView?.visibility = View.VISIBLE

        mIdlingResource?.setIdle(false)
    }

    open fun hideLoading() {
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