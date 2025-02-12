package com.example.moneymate.transaction.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneymate.transaction.dao.TransactionDao
import com.example.moneymate.transaction.entity.TransactionEntity


class TransactionRepository(private val transactionDao: TransactionDao) {

    private val _allTransactionsGroupByCategory = MutableLiveData<Map<Long, List<TransactionEntity>>>()
    val allTransactionsGroupByCategory: LiveData<Map<Long, List<TransactionEntity>>> = _allTransactionsGroupByCategory

    fun loadAllTransactionGroupByCategory() {
        transactionDao.getAllTransaction().observeForever { transactions ->
            val groupedTransactions = transactions.groupBy { it.categoryID ?: 0L }
            _allTransactionsGroupByCategory.postValue(groupedTransactions)
        }
    }
    suspend fun insert(transactionEntity: TransactionEntity) {
        transactionDao.insertTransaction(transactionEntity)
    }
}
