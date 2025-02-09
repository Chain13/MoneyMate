package com.example.moneymate.transaction.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moneymate.transaction.entity.TransactionTypeEntity

@Dao
interface TransactionTypeDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTransactionType(transactionTypeEntity: TransactionTypeEntity)
    @Query("SELECT * FROM transaction_type")
    fun getAllTransactionType(): LiveData<List<TransactionTypeEntity>>

}
