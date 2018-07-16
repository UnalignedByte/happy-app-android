package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

interface DataFetcherProtocol {
    fun fetchHappinessStatusJsonData(): Observable<Result<Boolean>>
}

class DataFetcher: DataFetcherProtocol {
    override fun fetchHappinessStatusJsonData(): Observable<Result<Boolean>> {
        return Observable.just(Result.success(false))
    }
}