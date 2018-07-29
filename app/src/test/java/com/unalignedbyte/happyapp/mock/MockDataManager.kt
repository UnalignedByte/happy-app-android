package com.unalignedbyte.happyapp.mock

import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.DataManagerProtocol
import com.unalignedbyte.happyapp.data.HappinessStatus
import com.unalignedbyte.happyapp.data.HappinessSubmission
import com.unalignedbyte.happyapp.data.UserLogin
import io.reactivex.Observable

class MockDataManager: DataManagerProtocol {
    override fun fetchHappinessStatus(): Observable<Result<HappinessStatus>> {
        return Observable.just(Result.Success(HappinessStatus(86, 102)))
    }

    override fun pushHappinessSubmission(submission: HappinessSubmission): Observable<Result<Unit>> {
        return Observable.just(Result.Success(Unit))
    }

    override fun pushUserLogin(userLogin: UserLogin): Observable<Result<Unit>> {
        return Observable.just(Result.Success(Unit))
    }
}