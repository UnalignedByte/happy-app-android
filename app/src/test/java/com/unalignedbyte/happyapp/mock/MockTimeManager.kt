package com.unalignedbyte.happyapp.mock

import java.util.*
import java.util.concurrent.TimeUnit
import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.TimeManagerProtocol
import io.reactivex.schedulers.Schedulers

class MockTimeManager : TimeManagerProtocol {
    var scheduler = Schedulers.computation()

    override fun isDayElapsedSince(date: Date): Observable<Result<Boolean>> {
        return Observable.interval(1, TimeUnit.SECONDS, scheduler)
                .map {
                    val current = Calendar.getInstance()
                    current.time = Date()
                    val then = Calendar.getInstance()
                    then.time = date
                    val timeDifference = current.timeInMillis - then.timeInMillis
                    Result.Success(timeDifference >= 2000)
                }
    }
}