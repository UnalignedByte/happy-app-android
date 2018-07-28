package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.UserManager

class UserManagerTests {
    @Test
    fun testCanSubmitWithoutDependencies() {
        val userManager = UserManager()
        val observer = TestObserver<Result<Boolean>>()
        userManager.canSubmit.subscribe(observer)
        observer.assertValue(Result.Failure())
    }
}