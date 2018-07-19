package com.unalignedbyte.happyapp

import org.junit.Test
import io.reactivex.observers.TestObserver
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.DataPusher

class DataPusherTests {
    @Test fun testPushHappinessSubmissionWithoutWebApi() {
        val dataPusher = DataPusher()
        val testObserver = TestObserver<Result<Unit>>()
        dataPusher.pushHappinessSubmissionJsonData(ByteArray(0)).subscribe(testObserver)
        testObserver.assertValue(Result.failure())
    }
}