package com.example.moneymate.transaction


class TransactionRepository(private val TransactionRepository: TransactionDao) {

    suspend fun insert(transactionEntity: TransactionEntity) {
        TransactionRepository.insertTransaction(transactionEntity)
    }

}
