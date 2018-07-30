package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

interface DataPusherProtocol {
    fun pushHappinessSubmissionJsonData(jsonData: ByteArray): Observable<Result<Unit>>
}

class DataPusher : DataPusherProtocol {
    var webApi: WebApiProtocol? = null

    // DataPusherProtocol
    override fun pushHappinessSubmissionJsonData(jsonData: ByteArray): Observable<Result<Unit>> {
        val webApi = webApi
        if (webApi != null) {
        }
        return Observable.just(Result.Failure())
    }
}