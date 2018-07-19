package com.unalignedbyte.happyapp.mock

import io.reactivex.Observable
import com.unalignedbyte.happyapp.data.DataFetcherProtocol
import com.unalignedbyte.happyapp.core.Result

class MockInvalidDataFetcher: DataFetcherProtocol {
    override fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>> {
        return Observable.just(Result.failure())
    }
}