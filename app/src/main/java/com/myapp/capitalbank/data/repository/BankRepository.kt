package com.myapp.capitalbank.data.repository

import com.myapp.capitalbank.data.local.BankDao
import com.myapp.capitalbank.data.model.Account
import com.myapp.capitalbank.data.model.Transaction
import com.myapp.capitalbank.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The primary data gateway for the application.
 * Manages the flow of data between the local Room database and (mocked) remote services.
 */
@Singleton
class BankRepository @Inject constructor(
    private val bankDao: BankDao
) {
    fun getUser(): Flow<User?> = bankDao.getUser()
    
    fun getAccounts(userId: String): Flow<List<Account>> = bankDao.getAccounts(userId)
    
    fun getTransactions(accountId: String): Flow<List<Transaction>> = bankDao.getTransactions(accountId)

    suspend fun refreshData() {
        // In a real app, this would call Retrofit. 
        // For this demo, we'll prepopulate Room with dummy data if empty.
        val existingUser = bankDao.getUser().firstOrNull()
        if (existingUser == null) {
            val dummyUser = User("1", "John Doe", "john.doe@example.com", null, System.currentTimeMillis())
            bankDao.insertUser(dummyUser)
            
            val dummyAccounts = listOf(
                Account("acc1", "1", "1234567890", 5420.50, "USD", "Checking"),
                Account("acc2", "1", "0987654321", 12500.00, "USD", "Savings")
            )
            bankDao.insertAccounts(dummyAccounts)
            
            val dummyTransactions = listOf(
                Transaction("t1", "acc1", 45.00, "Debit", "Food", "Starbucks", System.currentTimeMillis() - 3600000, "Completed"),
                Transaction("t2", "acc1", 120.00, "Debit", "Shopping", "Amazon", System.currentTimeMillis() - 86400000, "Completed"),
                Transaction("t3", "acc1", 2000.00, "Credit", "Transfer", "Salary", System.currentTimeMillis() - 172800000, "Completed")
            )
            bankDao.insertTransactions(dummyTransactions)
        }
    }
}
