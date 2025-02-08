package com.example.moneymate.transaction

import androidx.room.*

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): kotlinx.coroutines.flow.Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE type = :type ORDER BY date DESC")
    fun getTransactionsByType(type: String): kotlinx.coroutines.flow.Flow<List<Transaction>>

    @Query("SELECT * FROM transactions WHERE category = :category ORDER BY date DESC")
    fun getTransactionsByCategory(category: String): kotlinx.coroutines.flow.Flow<List<Transaction>>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Expense'")
    fun getTotalExpenses(): kotlinx.coroutines.flow.Flow<Double?>

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Income'")
    fun getTotalIncome(): kotlinx.coroutines.flow.Flow<Double?>
}
