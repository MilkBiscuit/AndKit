package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.cheng.httpproject.R
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.squareup.duktape.Duktape
import okio.Okio
import java.io.IOException


class SingleFragmentActivity : BaseActivity() {

    lateinit var duktape: Duktape

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_single_fragment)
    }

    override fun onStart() {
        super.onStart()

        duktape = Duktape.create()
        try {
            evaluateAsset("PlexureModuleConvert.js")
            val result = duktape.evaluate("convertExample();", "?")
            if (result != null) {
                Log.e("Greeting", result.toString())
                showToast(result.toString())
            }
        } finally {
            duktape.close()
        }
    }

    fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.single_fragment, fragment)
    }

    @Throws(IOException::class)
    private fun evaluateAsset(file: String) {
        var script: String
        Okio.buffer(Okio.source(assets.open(file))).use { source ->
            script = source.readUtf8()
            duktape.evaluate(script, file)
        }
    }

}
