package com.example.moneymate.transaction.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertCategory(categoryEntity: CategoryEntity)
    @Query("SELECT * FROM category")
    fun getAllCategory(): LiveData<List<CategoryEntity>>
}