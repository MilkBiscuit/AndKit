package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import com.cheng.httpproject.R
import com.cheng.httpproject.applySchedulers
import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.service.BibleService

class BibleActivity : BaseActivity() {

    var tvMain: TextView? = null
    val webService = BibleService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)

        tvMain = findViewById(R.id.tv_main)
        loadingView = findViewById(R.id.layout_loading)

        getChapter()
    }

    private fun getChapter() {
        showLoading()
        val observable = webService.fetchChapter(BibleConstants.BIBLE_AUTH_HEADER, BibleConstants.BIBLE_ID, "MAT.12")
        val disposable = observable.applySchedulers()
                .subscribe({result ->
                    tvMain?.text = result?.data?.content
                    tvMain?.movementMethod = ScrollingMovementMethod()
                    hideLoading()
                }, {error ->
                    tvMain?.text = error.message
                    hideLoading()
                })
        addDisposable(disposable)
    }

}
