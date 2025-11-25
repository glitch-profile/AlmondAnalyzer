package com.glitchdev.almondanalyzer.expenses.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val fieldId: Long,         // 🔗 связь с полем
    val description: String,
    val amount: Double
)