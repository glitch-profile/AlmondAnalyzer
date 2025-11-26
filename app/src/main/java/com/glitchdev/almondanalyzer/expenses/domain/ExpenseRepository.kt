package com.glitchdev.almondanalyzer.expenses.domain

import com.glitchdev.almondanalyzer.expenses.data.Expense

interface ExpenseRepository {
    suspend fun addExpense(expense: Expense): Long
    suspend fun deleteExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun getAllExpenses(): List<Expense>
    suspend fun getAllExpensesForField(fieldId: Long): List<Expense>
}