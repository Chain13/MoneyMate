package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.CategoryWithTypeRepository


class TransactionTypeListViewModel(application: Application) : ViewModel() {
    private val categoryWithTypeRepository: CategoryWithTypeRepository
    val categoryMap: LiveData<Map<TransactionTypeEntity, List<CategoryEntity>>>

    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val categoryWithTypeDao = moneyMateDatabase.categoryWithTypeDao()
        categoryWithTypeRepository = CategoryWithTypeRepository(categoryWithTypeDao)
        categoryMap = categoryWithTypeRepository.categoryMap
    }

}