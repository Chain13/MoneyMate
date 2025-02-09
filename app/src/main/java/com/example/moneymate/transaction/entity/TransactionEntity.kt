package com.example.moneymate.transaction.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "type_id") val typeId: Long?,
    @ColumnInfo(name = "date") val date: Long
)
