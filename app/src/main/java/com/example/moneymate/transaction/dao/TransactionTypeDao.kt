package com.example.moneymate.transaction.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.moneymate.transaction.entity.TransactionTypeEntity

@Dao
interface TransactionTypeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTransactionType(transactionTypeEntity: TransactionTypeEntity)

}
