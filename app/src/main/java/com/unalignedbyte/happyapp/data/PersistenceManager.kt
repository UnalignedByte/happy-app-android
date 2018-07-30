package com.unalignedbyte.happyapp.data

import java.util.*
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import com.unalignedbyte.happyapp.core.Result

interface PersistenceManagerProtocol {
    val submissionDate: Observable<Result<Date>>
    fun saveSubmissionDate(date: Date)
}

class PersistenceManager : PersistenceManagerProtocol {
    private val dateSubject = BehaviorSubject.create<Result<Date>>()

    init {
        dateSubject.onNext(Result.Failure())
    }

    // PersistenceManagerProtocol
    override val submissionDate: Observable<Result<Date>> = dateSubject

    override fun saveSubmissionDate(date: Date) {
        dateSubject.onNext(Result.Success(date))
    }
}