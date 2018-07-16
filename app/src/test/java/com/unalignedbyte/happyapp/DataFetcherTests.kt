package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.data.DataFetcher

class DataFetcherTests {
    @Test fun testFetchHappinessStatusWithoutWebApi() {
        val dataFetcher = DataFetcher()

        val testObserver = TestObserver<Boolean>()
        dataFetcher.fetchHappinessStatusJsonData().subscribe(testObserver)
        testObserver.assertValue(false)
    }
}