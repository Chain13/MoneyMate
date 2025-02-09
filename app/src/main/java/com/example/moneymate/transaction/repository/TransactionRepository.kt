package com.example.moneymate.transaction.repository

import com.example.moneymate.transaction.dao.TransactionDao
import com.example.moneymate.transaction.entity.TransactionEntity


class TransactionRepository(private val TransactionRepository: TransactionDao) {

    suspend fun insert(transactionEntity: TransactionEntity) {
        TransactionRepository.insertTransaction(transactionEntity)
    }

}
