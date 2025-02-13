package com.example.moneymate.transaction.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWithinCategoryEntity(
    @Embedded
    val categoryEntity: CategoryEntity,
    @Relation(parentColumn = "id", entityColumn = "category_id")
    val transactionWithinCategory: List<TransactionEntity>
)