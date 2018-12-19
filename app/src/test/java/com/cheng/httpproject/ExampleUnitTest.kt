package com.cheng.httpproject

import com.cheng.httpproject.constant.BibleConstants
import com.cheng.httpproject.model.GivingTransaction
import com.cheng.httpproject.service.BibleService
import com.cheng.httpproject.service.GivingApiService
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

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
