package com.unalignedbyte.happyapp.data

import io.reactivex.Observable
import kotlinx.serialization.json.JSON
import com.unalignedbyte.happyapp.core.Result
import com.unalignedbyte.happyapp.data.entities.HappinessStatus
import com.unalignedbyte.happyapp.data.entities.HappinessSubmission
import com.unalignedbyte.happyapp.data.entities.UserLogin

interface DataManagerProtocol {
    fun fetchHappinessStatus(): Observable<Result<HappinessStatus>>
    fun pushHappinessSubmission(submission: HappinessSubmission): Observable<Result<Unit>>
    fun pushUserLogin(userLogin: UserLogin): Observable<Result<Unit>>
}

class DataManager : DataManagerProtocol {
    var dataFetcher: DataFetcherProtocol? = null
    var dataPusher: DataPusherProtocol? = null

    // DataManagerProtocol
    override fun fetchHappinessStatus(): Observable<Result<HappinessStatus>> {
        val dataFetcher = dataFetcher
        if (dataFetcher != null) {
            return dataFetcher.fetchHappinessStatusJsonData()
                    .map { result ->
                        val value = result.value
                        if (value != null) {
                            val jsonString = String(value)
                            val happinessStatus = JSON.parse<HappinessStatus>(jsonString)
                            return@map Result.Success(happinessStatus)
                        }

                        return@map Result.Failure<HappinessStatus>()
                    }
        }

        return Observable.just(Result.Failure())
    }

    override fun pushHappinessSubmission(submission: HappinessSubmission): Observable<Result<Unit>> {
        var dataPusher = dataPusher
        if (dataPusher != null) {
            val jsonData = JSON.stringify(submission).toByteArray(Charsets.UTF_8)
            return dataPusher.pushHappinessSubmissionJsonData(jsonData)
        }

        return Observable.just(Result.Failure())
    }

    override fun pushUserLogin(userLogin: UserLogin): Observable<Result<Unit>> {
        return Observable.just(Result.Success(Unit))
    }
}