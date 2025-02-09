package com.example.moneymate.transaction.repository

import androidx.lifecycle.LiveData
import com.example.moneymate.transaction.dao.TransactionTypeDao
import com.example.moneymate.transaction.entity.TransactionTypeEntity

class TransactionTypeRepository(private val transactionTypeDao: TransactionTypeDao) {
    val allTransactionType: LiveData<List<TransactionTypeEntity>> =
        transactionTypeDao.getAllTransactionType()

    suspend fun insert(transactionTypeEntity: TransactionTypeEntity) {
        transactionTypeDao.insertTransactionType(transactionTypeEntity)
    }
}