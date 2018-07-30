package com.unalignedbyte.happyapp.data

import java.util.*
import java.util.concurrent.TimeUnit
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import com.unalignedbyte.happyapp.core.Result

interface TimeManagerProtocol {
    fun isDayElapsedSince(date: Date): Observable<Result<Boolean>>
}

class TimeManager : TimeManagerProtocol {
    var scheduler = Schedulers.computation()

    private fun midnightFromDate(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.HOUR, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    // TimeManagerProtocol
    override fun isDayElapsedSince(date: Date): Observable<Result<Boolean>> {
        return Observable.interval(1, TimeUnit.SECONDS, scheduler)
                .map {
                    val todayMorning = midnightFromDate(Date())
                    val thenMorning = midnightFromDate(date)
                    val isAfter = todayMorning.after(thenMorning)
                    return@map Result.Success(isAfter)
                }
    }
}