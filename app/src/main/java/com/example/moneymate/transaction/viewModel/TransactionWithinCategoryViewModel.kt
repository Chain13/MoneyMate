package com.example.moneymate.transaction.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moneymate.transaction.MoneyMateDatabase
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionWithinCategoryEntity
import com.example.moneymate.transaction.repository.TransactionRepository
import com.example.moneymate.transaction.repository.TransactionWithinCategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date

class TransactionWithinCategoryViewModel(application: Application): ViewModel() {
    private val transactionRepository: TransactionRepository
    val transactionWithinCategoryEntityList: MutableLiveData<List<TransactionEntity>>
    val categoryEntity: MutableLiveData<CategoryEntity>
    private var _month = MutableStateFlow(1)
    val month = _month
    private val _year = MutableStateFlow(1970)
    var year = _year

    init {
        val moneyMateDatabase = MoneyMateDatabase.getDatabase(application)
        val transactionDao = moneyMateDatabase.transactionDao()
        transactionRepository = TransactionRepository(transactionDao)
        transactionWithinCategoryEntityList = transactionRepository.transactionWithinCategoryEntityList
        categoryEntity = transactionRepository.categoryEntity
        _month.value = LocalDate.now().month.value
        _year.value = LocalDate.now().year

    }
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun getTransactionWithinCategory(categoryId: Long) {
        val firstDayOfMonth = LocalDate.of(year.value, month.value,1)
        val startDate: Long = LocalDate.from(firstDayOfMonth).atStartOfDay().toEpochSecond(
            ZoneOffset.UTC) * 1000
        val endDate: Long = LocalDate.from(firstDayOfMonth).plusMonths(1).atStartOfDay().toEpochSecond(
            ZoneOffset.UTC) * 1000
// 1_738_368_000 1_740_787_200
        println("${startDate} ${endDate}")
//        println("${firstDayOfMonth}")
            transactionRepository.findAllTransactionWithinCategory(categoryId = categoryId, startDate = startDate, endDate = endDate)
    }

    fun getCategoryById(categoryId: Long) {
        transactionRepository.findCategoryById(categoryId = categoryId)
    }
    fun setMonthYear(month: Int, year: Int) {
        _month.value = month
        _year.value = year
    }

}
