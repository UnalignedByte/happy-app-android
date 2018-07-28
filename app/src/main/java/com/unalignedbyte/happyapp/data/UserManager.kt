package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

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

    override val canSubmit: Observable<Result<Boolean>>
        get() {
            val timeManager = timeManager
            val persistenceManager = persistenceManager
            if (timeManager != null && persistenceManager != null) {
            }
            return Observable.just(Result.Failure())
        }
    override val isLoggedIn: Observable<Result<Boolean>>
        get() {
            return Observable.just(Result.Failure())
        }

    override fun submitHappiness(level: Int): Observable<Result<Unit>> {
        val dataManager = dataManager
        val persistenceManager = persistenceManager
        if(timeManager != null && persistenceManager != null) {
        }
        return Observable.just(Result.Failure())
    }

    override fun logIn() {
    }
}