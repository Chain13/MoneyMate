package com.example.moneymate.transaction.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneymate.transaction.dao.TransactionTypeDao
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TransactionTypeRepository(private val transactionTypeDao: TransactionTypeDao) {
    val allTransactionType: LiveData<List<TransactionTypeEntity>> =
        transactionTypeDao.getAllTransactionType()
    val _allTransactionGroupByCategory = MutableLiveData<Map<Long, List<TransactionEntity>>>()
    val allTransactionGroupByCategory: LiveData<Map<Long, List<TransactionEntity>>> = _allTransactionGroupByCategory
    val coroutineScope = CoroutineScope(Dispatchers.Main)
    suspend fun insert(transactionTypeEntity: TransactionTypeEntity) {
        transactionTypeDao.insertTransactionType(transactionTypeEntity)
    }

    private fun loadTransactionGroupByCategory(): Deferred<MutableMap<Long, MutableList<TransactionEntity>>> =
        coroutineScope.async(Dispatchers.IO) {
            var totalResult = mutableMapOf<Long, MutableList<TransactionEntity>>()
            allTransactionType.value?.forEach {
                val result: List<TransactionEntity> =
                    transactionTypeDao.getAllTransactionWithCategory(it.id)

                totalResult.compute(it.id) { _, existingList ->
                    (existingList ?: mutableListOf()).apply { addAll(result) }
                }
            }
            return@async totalResult
        }
    }
