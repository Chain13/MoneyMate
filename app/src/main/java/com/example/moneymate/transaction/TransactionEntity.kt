package com.example.moneymate.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,  // "Expense", "Income", "Transfer"
    val category: String,
    val amount: Double,
    val date: Long // Store as timestamp (Epoch time)
)
