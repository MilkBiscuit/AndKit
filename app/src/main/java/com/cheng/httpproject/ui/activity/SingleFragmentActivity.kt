package com.cheng.httpproject.ui.activity

import android.os.Bundle
import com.cheng.httpproject.R
import com.cheng.httpproject.model.PxOffer
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.ui.fragment.SimpleTextFragment
import com.cheng.httpproject.util.JsonUtil
import com.squareup.duktape.Duktape
import okio.Okio
import java.io.IOException


class SingleFragmentActivity : BaseActivity() {

    internal interface PlexureConverter {
        fun convertOffer(offerString: String): String?
    }

    lateinit var duktape: Duktape

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_single_fragment)
    }

    override fun onStart() {
        super.onStart()

        duktape = Duktape.create()
        try {
            val offer = PxOffer()
            val jsonObject = JsonUtil.objectToJson(offer)
            evaluateAsset("PlexureModuleConvert.js")

            // Connect our interface to a JavaScript object called Utf8.
            val utf8 = duktape.get("PlexureConverter", PlexureConverter::class.java)
            val result = utf8.convertOffer(jsonObject)
            if (result != null) {
                val fragment = SimpleTextFragment.newInstance(result.toString())
                replaceFragment(R.id.single_fragment, fragment)
            }
        } finally {
            duktape.close()
        }
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
