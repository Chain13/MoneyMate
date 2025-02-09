package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.TransactionTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TransactionTypeListViewModel(application: Application): ViewModel() {
    private val _transactionTypeList = MutableStateFlow<List<TransactionTypeEntity>>(emptyList())
    val transactionTypeList: StateFlow<List<TransactionTypeEntity>> = _transactionTypeList
    private val repository: TransactionTypeRepository
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        repository = TransactionTypeRepository(transactionTypeDao)
    }

}