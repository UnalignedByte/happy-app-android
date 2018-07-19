package com.unalignedbyte.happyapp.mock

import java.io.File
import io.reactivex.Observable
import com.unalignedbyte.happyapp.data.DataFetcherProtocol
import com.unalignedbyte.happyapp.core.Result

class MockDataFetcher: DataFetcherProtocol {
    override fun fetchHappinessStatusJsonData(): Observable<Result<ByteArray>> {
        val fileUrl = this.javaClass.classLoader.getResource("happiness_status.json")
        val data = File(fileUrl.toURI()).readBytes()
        return Observable.just(Result.Success(data))
    }
}