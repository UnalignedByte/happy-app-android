package com.unalignedbyte.happyapp

import org.junit.Test
import java.util.*
import java.util.concurrent.TimeUnit
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import com.unalignedbyte.happyapp.core.*
import com.unalignedbyte.happyapp.data.*

class TimeManagerTests {
    @Test
    fun testIsDayElapsedSinceNow() {
        val timeManager = TimeManager()

        val scheduler = TestScheduler()
        timeManager.scheduler = scheduler
        val observer = TestObserver<Result<Boolean>>()
        timeManager.isDayElapsedSince(Date()).subscribe(observer)

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        observer.assertValue(Result.Success(false))
    }

    @Test
    fun testIsDayElapsedSinceYesterday() {
        val timeManager = TimeManager()

        val scheduler = TestScheduler()
        timeManager.scheduler = scheduler
        val observer = TestObserver<Result<Boolean>>()
        timeManager.isDayElapsedSince(yesterdayDate).subscribe(observer)

        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        observer.assertValue(Result.Success(true))
    }

    // Private
    private val yesterdayDate: Date
        get() {
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            return calendar.time
        }
}