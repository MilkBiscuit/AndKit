package com.cheng.httpproject.api

import com.cheng.httpproject.service.PlexureService
import org.junit.Test

class PlexureApiUnitTest {

    @Test
    fun testFetchStores() {
        val plexureService = PlexureService.getInstance()
        val observable = plexureService.fetchStores(26.333351598841787, 127.79896146273005)
        val testObserver = observable.test()

        testObserver.assertNoErrors()
                .assertSubscribed()
                .assertValue{result ->
                    val storeNum = result.size
                    storeNum in 1..100
        }
    }

}
