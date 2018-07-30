package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

interface DataFetcherProtocol {
    fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>>
}

class DataFetcher : DataFetcherProtocol {
    var webApi: WebApiProtocol? = null

    // DataFetcherProtocol
    override fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>> {
        val webApi = webApi
        if (webApi != null) {
        }
        return Observable.just(Result.Failure())
    }
}