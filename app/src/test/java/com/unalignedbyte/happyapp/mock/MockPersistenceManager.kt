package com.unalignedbyte.happyapp.mock

import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.PersistenceManagerProtocol
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class MockPersistenceManager: PersistenceManagerProtocol {
    private val dateSubject = BehaviorSubject.create<Result<Date>>()

    override val submissionDate: Observable<Result<Date>> = dateSubject

    override fun saveSubmissionDate(date: Date) {
        dateSubject.onNext(Result.Success(date))
    }

    init {
        dateSubject.onNext(Result.Failure())
    }
}