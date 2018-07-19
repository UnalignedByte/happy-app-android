package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.DataFetcher

class DataFetcherTests {
    @Test fun testFetchHappinessStatusWithoutWebApi() {
        val dataFetcher = DataFetcher()

        val testObserver = TestObserver<Result<ByteArray>>()
        dataFetcher.fetchHappinessStatusJsonData().subscribe(testObserver)
        testObserver.assertValue(Result.failure())
    }
}