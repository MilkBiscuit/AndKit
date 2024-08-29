package com.cheng.andkit.sample.ui.activity.bible

import android.os.Bundle
import android.widget.TextView
import com.cheng.andkit.log.Lumberjack
import com.cheng.andkit.network.RestApiHelper
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.ui.activity.BaseActivity
import com.cheng.andkit.util.JsonUtil
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
            Lumberjack.w("GET api call failed: ${exception.localizedMessage}")
            if (exception is Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    hideLoading()
                    tvMain.text = exception.localizedMessage
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch(exceptionHandler) {
            val url = "${BibleConstants.BASE_URL}/bibles/${BibleConstants.BIBLE_ID}/chapters/Mat.7?content-type=text"
            val response = RestApiHelper.getApi(url,
                BibleConstants.BIBLE_AUTH_HEADER
            )
            withContext(Dispatchers.Main) {
                hideLoading()
                if (response.isSuccess) {
                    val bibleModelResult = JsonUtil.jsonToObject<BibleModelResult>(response.getOrThrow())
                    tvMain.text = bibleModelResult?.data?.content
                }
            }
        }
    }

}
