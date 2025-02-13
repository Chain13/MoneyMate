package com.example.moneymate.transaction.repository

import androidx.lifecycle.MutableLiveData
import com.example.moneymate.transaction.dao.TransactionWithinCategoryDao
import com.example.moneymate.transaction.entity.TransactionWithinCategoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TransactionWithinCategoryRepository(private val transactionWithinCategoryDao: TransactionWithinCategoryDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val transactionWithinCategoryEntity = MutableLiveData<List<TransactionWithinCategoryEntity>>()
    fun findAllTransactionWithinCategory(categoryId: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            transactionWithinCategoryEntity.value =
                asyncFindAllTransactionWithinCategory(categoryId).await()
        }
    }

    private fun asyncFindAllTransactionWithinCategory(categoryId: Long): Deferred<List<TransactionWithinCategoryEntity>> =
        coroutineScope.async(
            Dispatchers.IO
        ) {
            return@async transactionWithinCategoryDao.getTransactionWithinCategoryDao(categoryId)
        }
}