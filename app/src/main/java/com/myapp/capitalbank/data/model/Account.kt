package com.myapp.capitalbank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey val id: String,
    val userId: String,
    val accountNumber: String,
    val balance: Double,
    val currency: String,
    val accountType: String // "Savings", "Checking", etc.
)
