package com.example.moneymate.transaction

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.moneymate.transaction.dao.TransactionDao
import com.example.moneymate.transaction.dao.TransactionTypeDao
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity

@Database(
    entities = [TransactionEntity::class, TransactionTypeEntity::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(
            from = 1,
            to = 2,
            spec = MoneyMateDatabase.Migartion_1_2::class
        ),
        AutoMigration(
            from = 2,
            to = 3,
        )
    ]
)
abstract class MoneyMateDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionTypeDao(): TransactionTypeDao

    @RenameColumn(tableName = "transactions", fromColumnName = "type", toColumnName = "typeId")
    class Migartion_1_2 : AutoMigrationSpec
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
