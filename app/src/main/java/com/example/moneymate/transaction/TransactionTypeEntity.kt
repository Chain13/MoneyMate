package com.example.moneymate.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class TransactionTypeEntity {
    @Entity(tableName = "transaction_type")
    data class TransactionType(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        @ColumnInfo(name = "type_name") val typeName: String
    )

}