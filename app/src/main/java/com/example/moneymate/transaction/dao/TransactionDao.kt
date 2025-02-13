package com.example.moneymate.transaction.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionWithinCategoryEntity

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transactionEntity: TransactionEntity)
    @Query("SELECT * FROM transactions")
    fun getAllTransaction(): LiveData<List<TransactionEntity>>
    @Query("SELECT * FROM category INNER JOIN transactions ON category.id = transactions.category_id WHERE category.id = :categoryId ")
    fun getTransactionWithinCategoryDao(categoryId: Long): List<TransactionEntity>
    @Query("SELECT * FROM category WHERE category.id = :categoryId ")
    fun getCategoryById(categoryId: Long): List<CategoryEntity>

}
