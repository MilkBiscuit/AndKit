package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.widget.TextView
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.service.BibleService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import com.cheng.httpproject.util.applySchedulers

class BibleActivity : BaseActivity() {

    lateinit var tvMain: TextView
    val webService = BibleService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)

        loadingView = findViewById(R.id.layout_loading)
        tvMain = findViewById(R.id.tv_main)

        getChapter()
    }

    private fun getChapter() {
        showLoading()
        val observable = webService.fetchChapter(BibleConstants.BIBLE_AUTH_HEADER, BibleConstants.BIBLE_ID,
                "ISA.42")
        val disposable = observable.applySchedulers()
                .subscribe({result ->
                    tvMain.text = result?.data?.content
                    hideLoading()
                }, {error ->
                    tvMain.text = error.message
                    hideLoading()
                })
        addDisposable(disposable)
    }

}
