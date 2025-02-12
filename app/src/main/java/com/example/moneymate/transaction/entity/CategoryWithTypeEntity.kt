package com.example.moneymate.transaction.entity

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithTransactionTypeEntity (
    @Embedded val transactionTypeEntity: TransactionTypeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "transaction_type_id"
    )
    val categoryEntityList: List<CategoryEntity>
)