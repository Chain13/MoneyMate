package com.example.moneymate.transaction.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.moneymate.transaction.entity.CategoryWithTransactionTypeEntity

@Dao
interface CategoryWithTypeDao {
    @Transaction
    @Query("SELECT * FROM category INNER JOIN transaction_type ON transaction_type.id =  category.transaction_type_id ")
    fun getCategoryWithType(): LiveData<List<CategoryWithTransactionTypeEntity>>
}