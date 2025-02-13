package com.example.moneymate.transaction.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.moneymate.transaction.entity.TransactionWithinCategoryEntity


@Dao
interface TransactionWithinCategoryDao {
    @Transaction
    @Query("SELECT * FROM category INNER JOIN transactions ON category.id = transactions.category_id WHERE category.id = :categoryId ")
    fun getTransactionWithinCategoryDao(categoryId: Long): List<TransactionWithinCategoryEntity>
}