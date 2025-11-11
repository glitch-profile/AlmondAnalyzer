package com.glitchdev.almondanalyzer.expenses.domain

import com.glitchdev.almondanalyzer.expenses.data.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun addExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    fun getAllExpenses(): Flow<List<Expense>>
}