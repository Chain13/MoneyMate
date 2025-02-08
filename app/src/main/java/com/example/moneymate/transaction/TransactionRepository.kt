package com.example.moneymate.transaction


class TransactionRepository(private val transactionDao: TransactionDao) {

    suspend fun insert(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

}
