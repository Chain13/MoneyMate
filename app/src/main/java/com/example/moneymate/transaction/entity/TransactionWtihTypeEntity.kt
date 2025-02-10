package com.example.moneymate.transaction.entity

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionWtihTypeEntity (
    @Embedded val transactionTypeEntity: TransactionTypeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "type_id"
    )
    val transactionEntityList: List<TransactionEntity>
)