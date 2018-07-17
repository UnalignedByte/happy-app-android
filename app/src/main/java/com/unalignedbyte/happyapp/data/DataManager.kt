package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import com.unalignedbyte.happyapp.core.Result

interface DataManagerProtocol {
    fun fetchHappinessStatus(): Observable<Result<HappinessStatus>>
}

class DataManager: DataManagerProtocol {
    var dataFetcher: DataFetcherProtocol? = null

    override fun fetchHappinessStatus(): Observable<Result<HappinessStatus>> {
        val dataFetcher = dataFetcher
        if(dataFetcher != null) {
            return dataFetcher.fetchHappinessStatusJsonData()
                    .map { result ->
                        val value = result.value
                        if(value != null) {
                            val jsonString = String(value)
                            return@map Result.success(HappinessStatus(86))
                        }

                        return@map Result.failure<HappinessStatus>()
                    }
        }

        return Observable.just(Result.failure())
    }
}