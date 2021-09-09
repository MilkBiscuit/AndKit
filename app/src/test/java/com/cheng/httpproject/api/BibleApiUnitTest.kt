package com.cheng.httpproject.api

import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.model.GivingTransaction
import com.cheng.httpproject.service.BibleService
import com.cheng.httpproject.service.GivingApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class BibleApiUnitTest {

    @Test
    fun testFetchChapter() {
        val BIBLE_ID_WEB = "9879dbb7cfe39e4d-04";
        val MAT_1_1 = "At that time, Jesus went on the Sabbath day through the grain fields. His disciples were hungry and began to pluck heads of grain and to eat.";
        val bibleService = BibleService.getInstance()
        val observable = bibleService.fetchChapter(BibleConstants.BIBLE_AUTH_HEADER, BIBLE_ID_WEB, "MAT.12")
        val testObserver = observable.test()

        testObserver.assertNoErrors()
                .assertSubscribed()
                .assertValue{result ->
                    val model = result.data
                    "Matthew 12" == model.reference && model.content.contains(MAT_1_1)
        }
    }

    @Test
    fun testGetChapter() {
        val BIBLE_ID_WEB = "9879dbb7cfe39e4d-04";
        val MAT_1_1 = "At that time, Jesus went on the Sabbath day through the grain fields. His disciples were hungry and began to pluck heads of grain and to eat.";
        val bibleService = BibleService.getInstance()
        runBlocking {
            val result = bibleService.getChapter(BibleConstants.BIBLE_AUTH_HEADER, BIBLE_ID_WEB, "MAT.12")
            val bibleModel = result?.data
            assertTrue("Matthew 12" == bibleModel!!.reference)
            assertTrue(bibleModel.content.contains(MAT_1_1))
        }
    }

    @Test
    fun testFetchTransactionToken() {
        val body = GivingTransaction("50335", "abc", "193879",
                "100", "2", "2", "", "2018-12-31",
                "1");
        val observable = GivingApiService.getInstance().fetchTransactionToken(body);
        val testObserver = observable.test()

        testObserver.assertNoErrors()
                .assertSubscribed()
                .assertValue{result ->
                    System.out.println("token is: ${result.token}")
                    result.token.isNotEmpty()
                }
    }

}
