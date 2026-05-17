package com.myapp.capitalbank.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.capitalbank.data.model.Account
import com.myapp.capitalbank.data.model.Transaction
import com.myapp.capitalbank.data.model.User

@Database(entities = [User::class, Account::class, Transaction::class], version = 1, exportSchema = false)
abstract class BankDatabase : RoomDatabase() {
    abstract fun bankDao(): BankDao
}
