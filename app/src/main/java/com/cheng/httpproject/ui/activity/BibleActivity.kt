package com.cheng.httpproject.ui.activity

import android.os.Bundle
import android.widget.TextView
import com.cheng.andkit.network.NetworkManager
import com.cheng.andkit.network.model.Failure
import com.cheng.andkit.network.model.Success
import com.cheng.andkit.util.JsonUtil
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.model.BibleModelResult
import com.cheng.httpproject.ui.activity.base.BaseActivity
import kotlinx.coroutines.*

class BibleActivity : BaseActivity() {

    private lateinit var tvMain: TextView

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
            val url = "https://api.scripture.api.bible/v1/bibles/${BibleConstants.BIBLE_ID}/chapters/Mat.7?content-type=text"
            val response = NetworkManager.getApi(url,
                BibleConstants.BIBLE_AUTH_HEADER
            )
            withContext(Dispatchers.Main) {
                hideLoading()
                when (response) {
                    is Success -> {
                        val bibleModelResult = JsonUtil.jsonToObject<BibleModelResult>(response.value)
                        tvMain.text = bibleModelResult?.data?.content
                    }
                    is Failure -> {

                    }
                }
            }
        }
    }

}
