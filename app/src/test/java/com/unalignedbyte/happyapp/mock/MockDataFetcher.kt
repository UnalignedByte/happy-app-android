package com.unalignedbyte.happyapp.mock

import io.reactivex.Observable
import java.io.File
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.DataFetcherProtocol

class MockDataFetcher: DataFetcherProtocol {
    override fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>> {
        val fileUrl = this.javaClass.classLoader.getResource("happiness_status.json")
        val data = File(fileUrl.toURI()).readBytes()
        return Observable.just(Result.success(data))
    }
}