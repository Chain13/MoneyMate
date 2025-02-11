package com.example.moneymate.transaction.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions", foreignKeys = [

        ForeignKey(
            entity = TransactionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("category_id"),
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "category_id") val categoryID: Long?,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "description") val description: String?
)
