package com.cheng.httpproject

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.service.BibleApiInterface
import com.cheng.httpproject.service.BibleService
import com.cheng.httpproject.ui.BaseActivity

class MainActivity : BaseActivity() {

    var tvMain: TextView? = null
    var webService: BibleApiInterface? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webService = BibleService.getInstance()
        tvMain = findViewById(R.id.tv_main)
        loadingView = findViewById(R.id.layout_loading)

        getChapter()
    }

    fun getChapter() {
        val observable = webService?.fetchChapter(BibleConstants.BIBLE_AUTH_HEADER, BibleConstants.BIBLE_ID, "MAT.12")
        showLoading()

        observable!!.applySchedulers()
                .subscribe({result ->
                    tvMain?.text = result?.data?.content
                    tvMain?.movementMethod = ScrollingMovementMethod()
                    hideLoading()
                }, {error ->
                    tvMain?.text = error.message
                    hideLoading()
                })
    }

}
