package com.myapp.capitalbank.data.remote

import com.myapp.capitalbank.data.model.Account
import com.myapp.capitalbank.data.model.Transaction
import com.myapp.capitalbank.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface BankApiService {
    @GET("user/profile")
    suspend fun getUserProfile(): User

    @GET("user/accounts")
    suspend fun getAccounts(): List<Account>

    @GET("accounts/{accountId}/transactions")
    suspend fun getTransactions(@Path("accountId") accountId: String): List<Transaction>
}
