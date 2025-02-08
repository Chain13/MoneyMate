package com.example.moneymate.transaction

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
abstract class MoneyMateDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: MoneyMateDatabase? = null

        fun getDatabase(context: Context): MoneyMateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyMateDatabase::class.java,
                    "money_mate_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
