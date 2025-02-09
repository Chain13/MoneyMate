package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.TransactionRepository
import com.example.moneymate.transaction.repository.TransactionTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TransactionCreateViewModel(application: Application) : ViewModel() {
    private val _amount = MutableStateFlow("")
    val amount = _amount
    private val _category = MutableStateFlow("")
    val category = _category
    private val _typeId = MutableStateFlow("")
    private val _isTransactionSaved = MutableStateFlow(false)
    private val transactionRepository: TransactionRepository
    private val transactionTypeRepository: TransactionTypeRepository
    val isTransactionSaved = _isTransactionSaved
    val allType: LiveData<List<TransactionTypeEntity>>
    private val _isError = MutableStateFlow(false)
    val isError = _isError
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionDao = moneyMateDatabase.transactionDao()
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        transactionRepository = TransactionRepository(transactionDao)
        transactionTypeRepository = TransactionTypeRepository(transactionTypeDao)
        allType = transactionTypeRepository.allTransactionType
    }

    fun setAmount(amount: String) {
        _amount.value = amount
    }

    fun setCategories(categories: String) {
        _category.value = categories
    }

    fun setType(type: String) {
        _typeId.value = type
    }

    fun saveTransaction() {
        val amount = _amount.value
        val category = _category.value
        val typeId = _typeId.value
        val date = System.currentTimeMillis()
        if(!amount.isDigitsOnly()) {
            _isError.value = true
            return
        }
        if (amount.isNotEmpty() && category.isNotEmpty() && typeId.isNotEmpty()) {
            val transactionEntity = TransactionEntity(
                amount = amount.toDouble(),
                category = category,
                typeId = typeId.toLong(),
                date = date
            )
            viewModelScope.launch {
                transactionRepository.insert(transactionEntity)
            }
            isTransactionSaved.value = true
            _isError.value = false
        }
        else {
            isTransactionSaved.value = false
        }

    }
}
