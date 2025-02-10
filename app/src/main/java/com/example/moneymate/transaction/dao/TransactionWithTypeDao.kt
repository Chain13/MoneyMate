package com.example.moneymate.transaction.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.moneymate.transaction.entity.TransactionTypeEntity

@Dao
interface TransactionWithTypeDao {
    @Transaction
    @Query("SELECT * FROM transaction_type")
    suspend fun getTransactionWithTypeList(): List<TransactionTypeEntity>
}