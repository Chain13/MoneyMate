package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.TransactionTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TransactionTypeCreateViewModel(application: Application): ViewModel() {
    private val _type = MutableStateFlow("")
    val type = _type
    private val _isTransactionTypeSaved = MutableStateFlow(false)
    val isTransactionTypeSaved = _isTransactionTypeSaved
    private val repository: TransactionTypeRepository
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        repository = TransactionTypeRepository(transactionTypeDao)
    }
    fun setType(type: String) {
        _type.value = type
    }
    fun saveTransactionType() {
        val type = _type.value
        if(type.isNotEmpty()) {
            val transactionType: TransactionTypeEntity = TransactionTypeEntity(
                typeName = type
            )
            viewModelScope.launch {
                repository.insert(transactionType)
                _isTransactionTypeSaved.value = true
            }
        }
        
    }
}