package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.DataManager
import com.unalignedbyte.happyapp.data.HappinessStatus
import com.unalignedbyte.happyapp.mock.MockDataFetcher

class DataManagerTests {
    @Test fun testFetchHappinessStatusWithoutFetcher() {
        val dataManager = DataManager()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.failure())
    }

    @Test fun testFetchHappinessStatusWithValidFetcher() {
        val dataManager = DataManager()
        dataManager.dataFetcher = MockDataFetcher()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.success(HappinessStatus(86, 102)))
    }
}