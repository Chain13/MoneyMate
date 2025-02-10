package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.TransactionTypeRepository
import kotlinx.coroutines.launch

class TransactionTypeCreateViewModel(application: Application): ViewModel() {
    private val repository: TransactionTypeRepository
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        repository = TransactionTypeRepository(transactionTypeDao)
    }

    fun saveTransactionType(type: String) {
        val type = type
        if(type.isNotEmpty()) {
            val transactionType: TransactionTypeEntity = TransactionTypeEntity(
                typeName = type
            )
            viewModelScope.launch {
                repository.insert(transactionType)
            }
        }
        
    }
}