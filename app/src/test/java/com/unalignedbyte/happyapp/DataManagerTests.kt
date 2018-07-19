package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.DataManager
import com.unalignedbyte.happyapp.data.HappinessStatus
import com.unalignedbyte.happyapp.data.HappinessSubmission
import com.unalignedbyte.happyapp.mock.MockDataFetcher

class DataManagerTests {
    // Fetcher
    @Test fun testFetchHappinessStatusWithoutDataFetcher() {
        val dataManager = DataManager()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.failure())
    }

    @Test fun testFetchHappinessStatusWithDataFetcher() {
        val dataManager = DataManager()
        dataManager.dataFetcher = MockDataFetcher()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.success(HappinessStatus(86, 102)))
    }

    // Pusher
    @Test fun testPushHappinessSubmissionWithoutDataPusher() {
        val dataManager = DataManager()
        val testObserver = TestObserver<Result<Void>>()
        val submission = HappinessSubmission(1)
        dataManager.pushHappinessSubmission(submission).subscribe(testObserver)
        testObserver.assertValue(Result.failure())
    }
}