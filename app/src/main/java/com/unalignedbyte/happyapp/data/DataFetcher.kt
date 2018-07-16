package com.unalignedbyte.happyapp.data

import io.reactivex.Observable

class DataFetcher {
    fun fetchHappinessStatusJsonData(): Observable<Boolean> {
        return Observable.just(false)
    }
}