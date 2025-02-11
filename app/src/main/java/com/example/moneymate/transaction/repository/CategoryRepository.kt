package com.example.moneymate.transaction.repository

import androidx.lifecycle.LiveData
import com.example.moneymate.transaction.dao.CategoryDao
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity

class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCategory: LiveData<List<CategoryEntity>> =
        categoryDao.getAllCategory()

    suspend fun insert(categoryEntity: CategoryEntity) {
        categoryDao.insertCategory(categoryEntity)
    }

}