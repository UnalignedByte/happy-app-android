package com.unalignedbyte.happyapp.data

import io.reactivex.Observable

interface DataFetcherProtocol {
    fun fetchHappinessStatusJsonData(): Observable<Boolean>
}

class DataFetcher: DataFetcherProtocol {
    override fun fetchHappinessStatusJsonData(): Observable<Boolean> {
        return Observable.just(false)
    }
}