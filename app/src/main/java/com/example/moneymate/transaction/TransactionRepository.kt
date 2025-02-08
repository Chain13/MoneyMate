package com.example.moneymate.transaction


class TransactionRepository(private val transactionDao: TransactionDao) {

    suspend fun insert(transactionEntity: TransactionEntity) {
        transactionDao.insertTransaction(transactionEntity)
    }

}
