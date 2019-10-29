package com.cheng.httpproject.ui.activity

import android.os.Bundle
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.activity.base.BaseActivity

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)
        setTitle(R.string.settings)
    }
}
