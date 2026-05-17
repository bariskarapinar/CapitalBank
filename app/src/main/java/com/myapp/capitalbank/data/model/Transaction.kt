package com.myapp.capitalbank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: String,
    val accountId: String,
    val amount: Double,
    val type: String, // "Debit", "Credit"
    val category: String, // "Food", "Shopping", "Transfer", etc.
    val merchant: String,
    val timestamp: Long,
    val status: String // "Completed", "Pending", "Failed"
)
