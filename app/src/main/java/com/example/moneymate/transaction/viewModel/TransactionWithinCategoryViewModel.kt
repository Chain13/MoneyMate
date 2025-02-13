package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionWithinCategoryEntity
import com.example.moneymate.transaction.repository.TransactionRepository
import com.example.moneymate.transaction.repository.TransactionWithinCategoryRepository

class TransactionWithinCategoryViewModel(application: Application): ViewModel() {
    private val transactionRepository: TransactionRepository
    val transactionWithinCategoryEntityList: MutableLiveData<List<TransactionEntity>>
    val categoryEntity: MutableLiveData<CategoryEntity>
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionDao = moneyMateDatabase.transactionDao()
        transactionRepository = TransactionRepository(transactionDao)
        transactionWithinCategoryEntityList = transactionRepository.transactionWithinCategoryEntityList
        categoryEntity = transactionRepository.categoryEntity
    }
    fun getTransactionWithinCategory(categoryId: Long) {
        transactionRepository.findAllTransactionWithinCategory(categoryId = categoryId)
    }
    fun getCategoryById(categoryId: Long) {
        transactionRepository.findCategoryById(categoryId = categoryId)
    }
}
