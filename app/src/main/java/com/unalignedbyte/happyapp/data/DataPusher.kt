package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

interface DataPusherProtocol {
    fun pushHappinessSubmissionJsonData(jsonData: ByteArray): Observable<Result<Void>>
}

class DataPusher: DataPusherProtocol {
    var webApi: WebApiProtocol? = null

    override fun pushHappinessSubmissionJsonData(jsonData: ByteArray): Observable<Result<Void>> {
        val webApi = webApi
        if (webApi != null) {
        }
        return Observable.just(Result.failure())
    }
}