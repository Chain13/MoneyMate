package com.example.moneymate.transaction.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.moneymate.transaction.dao.CategoryWithTypeDao
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.CategoryWithTransactionTypeEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity

class CategoryWithTypeRepository(private val categoryWithTypeDao: CategoryWithTypeDao) {
    val allCategoryWithType: LiveData<List<CategoryWithTransactionTypeEntity>> =
        categoryWithTypeDao.getCategoryWithType()
    val categoryMap: LiveData<Map<TransactionTypeEntity, List<CategoryEntity>>> = allCategoryWithType.map { categoryWithTypes ->
        // Map the CategoryWithTransactionTypeEntity list to a Map
        categoryWithTypes.associate { categoryWithType ->
            categoryWithType.transactionTypeEntity to categoryWithType.categoryEntityList
        }
    }
}