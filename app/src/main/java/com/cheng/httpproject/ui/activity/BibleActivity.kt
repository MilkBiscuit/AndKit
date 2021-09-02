package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.widget.TextView
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.service.BibleService
import com.cheng.httpproject.ui.activity.base.BaseActivity
import kotlinx.coroutines.*

class BibleActivity : BaseActivity() {

    private lateinit var tvMain: TextView
    private val bibleService = BibleService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bible)

        loadingView = findViewById(R.id.layout_loading)
        tvMain = findViewById(R.id.tv_main)

        getChapter()
    }

    private fun getChapter() {
        showLoading()
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            if (exception is Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    hideLoading()
                    tvMain.text = exception.localizedMessage
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch(exceptionHandler) {
            val response = bibleService.getChapter(BibleConstants.BIBLE_AUTH_HEADER, BibleConstants.BIBLE_ID, "Mat.7")
            withContext(Dispatchers.Main) {
                tvMain.text = response?.data?.content
                hideLoading()
            }
        }
    }

}
