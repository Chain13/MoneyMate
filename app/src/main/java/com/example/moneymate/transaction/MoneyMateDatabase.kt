package com.example.moneymate.transaction

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.moneymate.transaction.dao.CategoryDao
import com.example.moneymate.transaction.dao.CategoryWithTypeDao
import com.example.moneymate.transaction.dao.TransactionDao
import com.example.moneymate.transaction.dao.TransactionTypeDao
import com.example.moneymate.transaction.dao.TransactionWithTypeDao
import com.example.moneymate.transaction.dao.TransactionWithinCategoryDao
import com.example.moneymate.transaction.entity.CategoryEntity
import com.example.moneymate.transaction.entity.CategoryWithTransactionTypeEntity
import com.example.moneymate.transaction.entity.TransactionEntity
import com.example.moneymate.transaction.entity.TransactionTypeEntity

@Database(
    entities = [TransactionEntity::class, TransactionTypeEntity::class, CategoryEntity::class],
    version = 7,
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
        ),
        AutoMigration(
            from = 3,
            to = 4
        ),
        AutoMigration(
            from = 4,
            to = 5,
            spec = MoneyMateDatabase.Migration_4_5::class
        ),
        AutoMigration(
            from = 5,
            to = 6
        ),
        AutoMigration(
            from = 6,
            to = 7
        )
    ]
)
abstract class MoneyMateDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun transactionTypeDao(): TransactionTypeDao
    abstract fun transactionWithTypeDao(): TransactionWithTypeDao
    abstract fun categoryDao(): CategoryDao
    abstract fun categoryWithTypeDao(): CategoryWithTypeDao
    abstract fun transactionWithinCategoryDao(): TransactionWithinCategoryDao

    @RenameColumn(tableName = "transactions", fromColumnName = "type", toColumnName = "typeId")
    class Migartion_1_2 : AutoMigrationSpec

    @DeleteColumn(
        tableName = "transactions",
        columnName = "category"

    )
    @DeleteColumn(
        tableName = "transactions",
        columnName = "type_id"
    )
    class Migration_4_5 : AutoMigrationSpec
    companion object {
        @Volatile
        private var INSTANCE: MoneyMateDatabase? = null

        fun getDatabase(context: Context): MoneyMateDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyMateDatabase::class.java,
                    "money_mate_db"
                )
                    .createFromAsset("database/money_mate_db.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
