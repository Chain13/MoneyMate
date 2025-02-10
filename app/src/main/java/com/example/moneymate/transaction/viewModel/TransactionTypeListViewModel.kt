package com.example.moneymate.transaction.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.TransactionTypeRepository


class TransactionTypeListViewModel(application: Application): ViewModel() {
    val allTransactionType: LiveData<List<TransactionTypeEntity>>
    private val repository: TransactionTypeRepository
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        repository = TransactionTypeRepository(transactionTypeDao)
        allTransactionType = repository.allTransactionType
    }

}