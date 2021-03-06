package com.unalignedbyte.happyapp.mock

import io.reactivex.Observable
import com.unalignedbyte.happyapp.data.DataPusherProtocol
import com.unalignedbyte.happyapp.core.Result

class MockInvalidDataPusher: DataPusherProtocol {
    override fun pushHappinessSubmissionJsonData(jsonData: ByteArray): Observable<Result<Unit>> {
        return Observable.just(Result.Failure())
    }
}