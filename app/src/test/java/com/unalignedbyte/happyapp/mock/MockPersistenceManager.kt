package com.unalignedbyte.happyapp.mock

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.PersistenceManagerProtocol

class MockPersistenceManager: PersistenceManagerProtocol {
    private val dateSubject = BehaviorSubject.create<Result<Date>>()

    init {
        dateSubject.onNext(Result.Failure())
    }

    override val submissionDate: Observable<Result<Date>> = dateSubject

    override fun saveSubmissionDate(date: Date) {
        dateSubject.onNext(Result.Success(date))
    }
}