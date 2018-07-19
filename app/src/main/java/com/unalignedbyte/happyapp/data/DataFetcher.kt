package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

interface DataFetcherProtocol {
    fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>>
}

class DataFetcher: DataFetcherProtocol {
    var webApi: WebApiProtocol? = null

    override fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>> {
        val webApi = webApi
        if (webApi != null) {
            //webApi?.happinessStatusUrl?.openConnection()?.getInputStream()?.read()
            //ByteArrayOutputStream().toByteArray()
        }
        return Observable.just(Result.failure())
    }
}