package com.example.moneymate.transaction.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moneymate.transaction.dao.TransactionDao
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionWithinCategoryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class TransactionRepository(private val transactionDao: TransactionDao) {

    private val _allTransactionsGroupByCategory = MutableLiveData<Map<Long, List<TransactionEntity>>>()
    val allTransactionsGroupByCategory: LiveData<Map<Long, List<TransactionEntity>>> = _allTransactionsGroupByCategory
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val transactionWithinCategoryEntityList = MutableLiveData<List<TransactionEntity>>()
    val categoryEntity = MutableLiveData<CategoryEntity>()
    fun loadAllTransactionGroupByCategory() {
        transactionDao.getAllTransaction().observeForever { transactions ->
            val groupedTransactions = transactions.groupBy { it.categoryID ?: 0L }
            _allTransactionsGroupByCategory.postValue(groupedTransactions)
        }
    }
    suspend fun insert(transactionEntity: TransactionEntity) {
        transactionDao.insertTransaction(transactionEntity)
    }
    fun findCategoryById(categoryId: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            categoryEntity.value = asyncFindCategoryById(categoryId).await()
        }
    }
    private fun asyncFindCategoryById(categoryId: Long): Deferred<CategoryEntity> =
        coroutineScope.async(Dispatchers.IO) {
            return@async transactionDao.getCategoryById(categoryId).first()
        }

    fun findAllTransactionWithinCategory(categoryId: Long) {
        coroutineScope.launch(Dispatchers.Main) {
            transactionWithinCategoryEntityList.value =
                asyncFindAllTransactionWithinCategory(categoryId).await()
        }
    }

    private fun asyncFindAllTransactionWithinCategory(categoryId: Long): Deferred<List<TransactionEntity>> =
        coroutineScope.async(
            Dispatchers.IO
        ) {
            return@async transactionDao.getTransactionWithinCategoryDao(categoryId)
        }
}
