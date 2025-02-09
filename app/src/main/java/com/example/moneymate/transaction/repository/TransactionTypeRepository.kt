package com.example.moneymate.transaction.repository

import com.example.moneymate.transaction.dao.TransactionTypeDao
import com.example.moneymate.transaction.entity.TransactionTypeEntity

class TransactionTypeRepository(private val transactionTypeDao: TransactionTypeDao) {
    suspend fun insert(transactionTypeEntity: TransactionTypeEntity) {
        transactionTypeDao.insertTransactionType(transactionTypeEntity)
    }
}