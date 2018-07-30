package com.unalignedbyte.happyapp

import org.junit.Test
import java.util.*
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.PersistenceManager

class PersistenceManagerTests {
    @Test
    fun testSubmissionDateBeforeSaving() {
        val persistenceManager = PersistenceManager()

        val observer = TestObserver<Result<Date>>()
        persistenceManager.submissionDate.subscribe(observer)

        observer.assertValue(Result.Failure())
    }

    @Test
    fun testSubmissionDateAfterSaving() {
        val persistenceManager = PersistenceManager()

        val observer = TestObserver<Result<Date>>()
        val date = Date()
        persistenceManager.saveSubmissionDate(date)
        persistenceManager.submissionDate.subscribe(observer)

        observer.assertValue(Result.Success(date))
    }
}