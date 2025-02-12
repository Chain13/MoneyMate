package com.example.moneymate.transaction.viewModel

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity
import com.example.moneymate.transaction.repository.CategoryRepository
import com.example.moneymate.transaction.repository.TransactionRepository
import com.example.moneymate.transaction.repository.TransactionTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

class CategoryCreateViewModel(application: Application): ViewModel() {
    private val categoryRepository: CategoryRepository
    private val transactionTypeRepository: TransactionTypeRepository
    val allCategory: LiveData<List<CategoryEntity>>
    val allTransactionType: LiveData<List<TransactionTypeEntity>>
    private val _categorySavedStatus = MutableStateFlow("")
    val categorySavedStatus = _categorySavedStatus
    private val _isCategorySaved = MutableStateFlow(false)
    val isCategorySaved = _isCategorySaved
    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val categoryDao = moneyMateDatabase.categoryDao()
        val transactionTypeDao = moneyMateDatabase.transactionTypeDao()
        categoryRepository = CategoryRepository(categoryDao)
        transactionTypeRepository = TransactionTypeRepository(transactionTypeDao)
        allCategory = categoryRepository.allCategory
        allTransactionType = transactionTypeRepository.allTransactionType

    }
    fun saveCategory(categoryName: String?, transactionType: TransactionTypeEntity?) {

        categoryName?.let {
            var name = categoryName.trim()

            if(name.isEmpty()) {
                return
            }
            val categoryEntity = CategoryEntity(
                categoryName = name,
                transactionType_id = transactionType?.id
            )
            viewModelScope.launch{
                try {
                    categoryRepository.insert(categoryEntity)
                    isCategorySaved.value = true
                    _categorySavedStatus.value = "${name} Category Saved Successfully!"
                } catch (e: SQLiteConstraintException) {
                    isCategorySaved.value = false
                    _categorySavedStatus.value = "Saved FAILED!. ${name} repeatedly in database "
                } catch (e: Exception) {
                    isCategorySaved.value = false

                    _categorySavedStatus.value = "${name} Category Saved FAILED!"
                }
            }
        }

    }
}