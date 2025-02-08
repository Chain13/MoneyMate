package com.example.moneymate.transaction

class TransactionTypeRepository(private val transactionTypeDao: TransactionTypeDao) {
    suspend fun insert(transactionTypeEntity: TransactionTypeEntity) {
        transactionTypeDao.insertTransactionType(transactionTypeEntity)
    }
}