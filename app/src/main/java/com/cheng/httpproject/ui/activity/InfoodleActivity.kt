package com.cheng.httpproject.ui.activity

import android.os.Bundle
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.fragment.InfoodleAuthFragment

class InfoodleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_infoodle)
        putAuthFragment()
    }

    fun putAuthFragment() {
        val fragment = InfoodleAuthFragment.newInstance();
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.infoodle_fragment, fragment)
        transaction.commit()
    }

}
