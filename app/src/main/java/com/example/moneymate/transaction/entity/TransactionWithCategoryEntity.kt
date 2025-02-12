package com.example.moneymate.transaction.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithCategoryEntity (
    @Embedded val categoryEntity: CategoryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "category_id"
    )
    val transactionList: List<TransactionEntity>
)