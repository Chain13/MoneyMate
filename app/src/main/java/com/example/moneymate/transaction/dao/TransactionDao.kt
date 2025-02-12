package com.example.moneymate.transaction.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moneymate.transaction.entity.TransactionEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionEntity: TransactionEntity)
    @Query("SELECT * FROM transactions")
    fun getAllTransaction(): LiveData<List<TransactionEntity>>

}
