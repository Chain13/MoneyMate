package com.example.moneymate.transaction.dao

import androidx.room.*
import com.example.moneymate.transaction.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionEntity: TransactionEntity)

}
