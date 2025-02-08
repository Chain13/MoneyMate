package com.example.moneymate.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TransactionTypeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTransactionType(transactionTypeEntity: TransactionTypeEntity)

}
