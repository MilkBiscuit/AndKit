package com.cheng.httpproject.service

import com.cheng.httpproject.model.GivingToken
import com.cheng.httpproject.model.GivingTransaction
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface GivingApiInterface {

    @POST("financials/transaction/token")
    fun fetchTransactionToken(@Body transaction: GivingTransaction): Observable<GivingToken>

}