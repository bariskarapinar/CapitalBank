package com.myapp.capitalbank.data.local

import androidx.room.*
import com.myapp.capitalbank.data.model.Account
import com.myapp.capitalbank.data.model.Transaction
import com.myapp.capitalbank.data.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {
    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM accounts WHERE userId = :userId")
    fun getAccounts(userId: String): Flow<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccounts(accounts: List<Account>)

    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY timestamp DESC")
    fun getTransactions(accountId: String): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<Transaction>)
}
