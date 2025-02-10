package com.example.moneymate.transaction.viewModel

import android.app.Application
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

    private val _isTransactionSaved = MutableStateFlow(false)
    private val transactionRepository: TransactionRepository
    private val transactionTypeRepository: TransactionTypeRepository
    val isTransactionSaved = _isTransactionSaved
    val allType: LiveData<List<TransactionTypeEntity>>

    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionDao = moneyMateDatabase.transactionDao()
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        transactionRepository = TransactionRepository(transactionDao)
        transactionTypeRepository = TransactionTypeRepository(transactionTypeDao)
        allType = transactionTypeRepository.allTransactionType
    }



    fun saveTransaction(amount: Double, category: String, type: TransactionTypeEntity?) {
        if(type == null) {
            return
        }
        val date = System.currentTimeMillis()
            val transactionEntity = TransactionEntity(
                amount = amount.toDouble(),
                category = category,
                typeId = type.id,
                date = date
            )
            viewModelScope.launch {
                transactionRepository.insert(transactionEntity)
            }
            isTransactionSaved.value = true

    }
}
