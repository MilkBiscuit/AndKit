package com.cheng.httpproject.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.fragment.base.WebViewFragment

class SingleFragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_single_fragment)
    }

    override fun onStart() {
        super.onStart()

        val fragment = WebViewFragment.newInstance("file:///android_asset/index.html")
        replaceFragment(fragment)
    }

    fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.single_fragment, fragment)
    }

}
