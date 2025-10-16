package com.glitchdev.almondanalyzer.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glitchdev.almondanalyzer.fields.data.FieldDao
import com.glitchdev.almondanalyzer.fields.data.FieldEntity
import com.glitchdev.almondanalyzer.expenses.data.Expense
import com.glitchdev.almondanalyzer.expenses.data.ExpenseDao

@Database(
    entities = [FieldEntity::class, Expense::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fieldDao(): FieldDao
    abstract fun expenseDao(): ExpenseDao
}