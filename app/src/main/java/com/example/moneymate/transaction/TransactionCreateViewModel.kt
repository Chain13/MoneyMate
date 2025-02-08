package com.example.moneymate.transaction

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionCreateViewModel(application: Application) : ViewModel() {
    // State variables for the transaction form
    private val _transactionType = MutableStateFlow("")
    val transactionType: StateFlow<String> = _transactionType
    private val repository: TransactionRepository

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category

    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount

    private val _date = MutableStateFlow(System.currentTimeMillis()) // Default to current date
    val date: StateFlow<Long> = _date

    private val _isTransactionSaved = MutableStateFlow(false)
    val isTransactionSaved: StateFlow<Boolean> = _isTransactionSaved

    init {
        val transactionDb = MoneyMateDatabase.getDatabase(application)
        val transactionDao = transactionDb.transactionDao()
        repository = TransactionRepository(transactionDao)
    }
    // Functions to update form inputs
    fun setTransactionType(type: String) {
        _transactionType.value = type
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun setAmount(amount: String) {
        _amount.value = amount
    }

    fun setDate(date: Long) {
        _date.value = date
    }

    // Function to save the transaction
    fun saveTransaction() {
        val type = _transactionType.value
        val category = _category.value
        val amountValue = _amount.value.toDoubleOrNull()

        if (type.isNotEmpty() && category.isNotEmpty() && amountValue != null && amountValue > 0) {
            val transactionEntity = TransactionEntity(
                type = type,
                category = category,
                amount = amountValue,
                date = _date.value
            )

            viewModelScope.launch {
                repository.insert(transactionEntity)
                _isTransactionSaved.value = true
            }
        }
    }
}
