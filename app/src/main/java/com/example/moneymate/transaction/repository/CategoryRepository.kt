package com.example.moneymate.transaction.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.moneymate.transaction.dao.CategoryDao
import com.example.moneymate.transaction.entity.CategoryEntity

class CategoryRepository(private val categoryDao: CategoryDao) {
    val allCategory: LiveData<List<CategoryEntity>> =
        categoryDao.getAllCategory()
    val categoryMap: LiveData<Map<Long, CategoryEntity>> = allCategory.map { categoryList ->
        categoryList.associateBy { it.id }  // Converts list to Map<Long, CategoryEntity>
    }
    suspend fun insert(categoryEntity: CategoryEntity) {
        categoryDao.insertCategory(categoryEntity)
    }

}