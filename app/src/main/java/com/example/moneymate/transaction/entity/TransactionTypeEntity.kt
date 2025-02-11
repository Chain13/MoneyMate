package com.example.moneymate.transaction.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_type", indices = [Index(value = ["type_name"], unique = true)])
data class TransactionTypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "type_name") val typeName: String
)

