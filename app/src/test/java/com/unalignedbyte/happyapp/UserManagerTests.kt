package com.unalignedbyte.happyapp

import org.junit.Test
import java.util.concurrent.TimeUnit
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.TestScheduler
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.UserManager
import com.unalignedbyte.happyapp.mock.*

class UserManagerTests {
    @Test
    fun testCanSubmitWithoutDependencies() {
        val userManager = UserManager()
        val observer = TestObserver<Result<Boolean>>()
        userManager.canSubmit.subscribe(observer)
        observer.assertValue(Result.Failure())
    }

    @Test
    fun testCanSubmitWithDependencies() {
        val userManager = UserManager()

        userManager.dataManager = MockDataManager()

        val scheduler = TestScheduler()
        val timeManager = MockTimeManager()
        timeManager.scheduler = scheduler
        userManager.timeManager = timeManager

        userManager.persistenceManager = MockPersistenceManager()

        val observer = TestObserver<Result<Boolean>>()
        userManager.canSubmit.subscribe(observer)
        userManager.logIn()
        userManager.submitHappiness(1)
        scheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        observer.assertValues(Result.Success(false), Result.Success(true), Result.Success(false))
    }
}