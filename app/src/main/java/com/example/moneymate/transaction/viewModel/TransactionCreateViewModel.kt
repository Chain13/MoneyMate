package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.CategoryRepository
import com.example.moneymate.transaction.repository.TransactionRepository
import com.example.moneymate.transaction.repository.TransactionTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TransactionCreateViewModel(application: Application) : ViewModel() {

    private val _isTransactionSaved = MutableStateFlow(false)
    private val transactionRepository: TransactionRepository
    private val cagetoryRepository: CategoryRepository
    val isTransactionSaved = _isTransactionSaved
    val allCategory: LiveData<List<CategoryEntity>>

    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionDao = moneyMateDatabase.transactionDao()
        val categoryDao = moneyMateDatabase.categoryDao()
        transactionRepository = TransactionRepository(transactionDao)
        cagetoryRepository = CategoryRepository(categoryDao)
        allCategory = cagetoryRepository.allCategory
    }



    fun saveTransaction(amount: Double, category: CategoryEntity?, description: String?) {
        if(category == null) {
            return
        }
        val date = System.currentTimeMillis()
            val transactionEntity = TransactionEntity(
                amount = amount,
                categoryID = category.id,
                date = date,
                description = description
            )
            viewModelScope.launch {
                transactionRepository.insert(transactionEntity)
            }
            isTransactionSaved.value = true

    }
}
