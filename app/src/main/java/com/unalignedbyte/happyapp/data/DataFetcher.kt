package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.WebApi
import java.io.ByteArrayOutputStream

interface DataFetcherProtocol {
    fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>>
}

class DataFetcher: DataFetcherProtocol {
    var webApi: WebApiProtocol? = null

    override fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>> {
        //webApi?.happinessStatusUrl?.openConnection()?.getInputStream()?.read()
        //ByteArrayOutputStream().toByteArray()
        return Observable.just(Result.failure())
    }
}