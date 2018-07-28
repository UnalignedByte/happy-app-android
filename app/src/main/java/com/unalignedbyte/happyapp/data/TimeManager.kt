package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

interface TimeManagerProtocol {
    fun isDayElapsedSince(date: Date): Observable<Result<Boolean>>
}

class TimeManager : TimeManagerProtocol {
    var scheduler = Schedulers.computation()

    override fun isDayElapsedSince(date: Date): Observable<Result<Boolean>> {
        return Observable.interval(1, TimeUnit.SECONDS, scheduler)
            .map {
                val todayMorning = midnightFromDate(Date())
                val thenMorning = midnightFromDate(date)
                val isAfter = todayMorning.after(thenMorning)
                return@map Result.Success(isAfter)
            }
    }

    private fun midnightFromDate(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }
}