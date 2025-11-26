package com.glitchdev.almondanalyzer.expenses.data

import com.glitchdev.almondanalyzer.expenses.domain.ExpenseRepository
import kotlinx.coroutines.flow.MutableStateFlow

class ExpenseRepositoryImpl(
    private val expenseDao: ExpenseDao
) : ExpenseRepository {

    private val cachedExpenses = MutableStateFlow<List<Expense>>(emptyList())

    override suspend fun getAllExpenses(): List<Expense> {
        return expenseDao.getAllExpenses()
    }

    override suspend fun getAllExpensesForField(fieldId: Long): List<Expense> {
        return expenseDao.getAllExpensesForField(fieldId)
    }

    override suspend fun addExpense(expense: Expense): Long {
        return expenseDao.insertExpense(expense)
    }

    override suspend fun deleteExpense(expense: Expense) {
        expenseDao.deleteExpense(expense)
    }

    override suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    fun getCachedExpenses(): List<Expense> = cachedExpenses.value
}