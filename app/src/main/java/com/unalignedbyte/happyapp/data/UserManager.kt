package com.unalignedbyte.happyapp.data

import java.util.*
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.switchLatest
import io.reactivex.subjects.BehaviorSubject
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.entities.HappinessSubmission
import com.unalignedbyte.happyapp.data.entities.UserLogin

interface UserManagerProtocol {
    val canSubmit: Observable<Result<Boolean>>
    val isLoggedIn: Observable<Result<Boolean>>
    fun submitHappiness(level: Int): Observable<Result<Unit>>
    fun logIn()
}

class UserManager : UserManagerProtocol {
    var dataManager: DataManagerProtocol? = null
    var timeManager: TimeManagerProtocol? = null
    var persistenceManager: PersistenceManagerProtocol? = null
    private val isLoggedInVar = BehaviorSubject.create<Boolean>()

    init {
        isLoggedInVar.onNext(false)
    }

    // UserManagerProtocol
    override val canSubmit: Observable<Result<Boolean>>
        get() {
            val timeManager = timeManager
            val persistenceManager = persistenceManager
            if (timeManager != null && persistenceManager != null) {
                return Observable.combineLatest(isLoggedIn, persistenceManager.submissionDate,
                        BiFunction<Result<Boolean>, Result<Date>, Observable<Result<Boolean>>> { isLoggedInResult, dateResult ->
                            val isLoggedIn = isLoggedInResult.value
                            val date = dateResult.value
                            if (isLoggedIn != null) {
                                if (isLoggedIn && date != null) {
                                    return@BiFunction timeManager.isDayElapsedSince(date)
                                } else {
                                    return@BiFunction Observable.just(Result.Success(isLoggedIn))
                                }
                            }
                            return@BiFunction Observable.just(Result.Failure())
                        }).switchLatest()
            }
            return Observable.just(Result.Failure())
        }

    override val isLoggedIn: Observable<Result<Boolean>> = isLoggedInVar.map { Result.Success(it) }

    override fun submitHappiness(level: Int): Observable<Result<Unit>> {
        val dataManager = dataManager
        val persistenceManager = persistenceManager
        if (dataManager != null && persistenceManager != null) {
            val submission = HappinessSubmission(level)
            dataManager.pushHappinessSubmission(submission)
                    .subscribe {
                        persistenceManager.saveSubmissionDate(Date())
                    }
        }
        return Observable.just(Result.Failure())
    }

    override fun logIn() {
        val dataManager = dataManager
        if (dataManager != null) {
            val userLogin = UserLogin("dummy")
            dataManager.pushUserLogin(userLogin)
                    .subscribe {
                        val loggedIn = it != Result.Failure<Unit>()
                        isLoggedInVar.onNext(loggedIn)
                    }
        }
    }
}