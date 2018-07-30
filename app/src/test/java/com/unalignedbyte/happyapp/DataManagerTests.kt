package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.*
import com.unalignedbyte.happyapp.data.*
import com.unalignedbyte.happyapp.data.entities.HappinessStatus
import com.unalignedbyte.happyapp.data.entities.HappinessSubmission
import com.unalignedbyte.happyapp.mock.*

class DataManagerTests {
    // Fetcher
    @Test
    fun testFetchHappinessStatusWithoutDataFetcher() {
        val dataManager = DataManager()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.Failure())
    }

    @Test
    fun testFetchHappinessStatusWithInvalidDataFetcher() {
        val dataManager = DataManager()
        dataManager.dataFetcher = MockInvalidDataFetcher()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.Failure())
    }

    @Test
    fun testFetchHappinessStatusWithDataFetcher() {
        val dataManager = DataManager()
        dataManager.dataFetcher = MockDataFetcher()
        val testObserver = TestObserver<Result<HappinessStatus>>()
        dataManager.fetchHappinessStatus().subscribe(testObserver)
        testObserver.assertValue(Result.Success(HappinessStatus(86, 102)))
    }

    // Pusher
    @Test
    fun testPushHappinessSubmissionWithoutDataPusher() {
        val dataManager = DataManager()
        val testObserver = TestObserver<Result<Unit>>()
        val submission = HappinessSubmission(1)
        dataManager.pushHappinessSubmission(submission).subscribe(testObserver)
        testObserver.assertValue(Result.Failure())
    }

    @Test
    fun testPushHappinessSubmissionWithInvalidDataPusher() {
        val dataManager = DataManager()
        dataManager.dataPusher = MockInvalidDataPusher()
        val testObserver = TestObserver<Result<Unit>>()
        val submission = HappinessSubmission(1)
        dataManager.pushHappinessSubmission(submission).subscribe(testObserver)
        testObserver.assertValue(Result.Failure())
    }

    @Test
    fun testPushHappinessSubmission() {
        val dataManager = DataManager()
        dataManager.dataPusher = MockDataPusher()
        val testObserver = TestObserver<Result<Unit>>()
        val submission = HappinessSubmission(1)
        dataManager.pushHappinessSubmission(submission).subscribe(testObserver)
        testObserver.assertValue(Result.Success(Unit))
    }
}