package com.example.moneymate.transaction

import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    suspend fun insert(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun update(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

    fun getAllTransactions(): Flow<List<Transaction>> = transactionDao.getAllTransactions()

    fun getTransactionsByType(type: String): Flow<List<Transaction>> = transactionDao.getTransactionsByType(type)

    fun getTransactionsByCategory(category: String): Flow<List<Transaction>> = transactionDao.getTransactionsByCategory(category)

    fun getTotalExpenses(): Flow<Double?> = transactionDao.getTotalExpenses()

    fun getTotalIncome(): Flow<Double?> = transactionDao.getTotalIncome()
}
