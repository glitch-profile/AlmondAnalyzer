package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glitchdev.almondanalyzer.expenses.data.Expense
import com.glitchdev.almondanalyzer.expenses.domain.ExpenseRepository
import com.glitchdev.almondanalyzer.fields.domain.FieldRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class FieldInfoViewModel(
    private val fieldRepository: FieldRepository,
    private val expenseRepository: ExpenseRepository
): ViewModel() {

    private val _fieldInfoState = MutableStateFlow(FieldInfoState())
    val fieldState = _fieldInfoState.asStateFlow()

    fun loadDataForField(fieldId: Long) {
        loadFieldInfo(fieldId)
        loadExpensesForField(fieldId)
    }

    private fun loadFieldInfo(fieldId: Long) {
        viewModelScope.launch {
            _fieldInfoState.update { it.copy(isLoadingField = true) }
            val fieldData = fieldRepository.getFieldById(fieldId)
            _fieldInfoState.update {
                it.copy(
                    fieldInfo = fieldData,
                    isLoadingField = false
                )
            }
        }
    }

    private fun loadExpensesForField(fieldId: Long) {
        viewModelScope.launch {
            _fieldInfoState.update { it.copy(isLoadingExpenses = true) }
//            val expenses = expenseRepository.getAllExpensesForField(fieldId)
            val expenses = listOf(
                Expense(
                    id = 1,
                    date = "12-11-2025",
                    fieldId = 111,
                    description = "Первые затраты",
                    amount = 12000.00
                ),
                Expense(
                    id = 1,
                    date = "12-11-2025",
                    fieldId = 111,
                    description = "Вторые затраты",
                    amount = 23718.56
                ), Expense(
                    id = 1,
                    date = "12-11-2025",
                    fieldId = 111,
                    description = "Купили первые саженцы",
                    amount = 960000.0
                ),
                Expense(
                    id = 1,
                    date = "12-11-2025",
                    fieldId = 111,
                    description = "Обрамление грядок",
                    amount = 1000.0
                )
            )
            delay(3000)
            _fieldInfoState.update {
                it.copy(
                    expenses = expenses,
                    isLoadingExpenses = false
                )
            }
        }
    }

    fun addExpenses(
        description: String,
        amount: Double,
        timestamp: Long
    ) {
        viewModelScope.launch {
            val expense = generateExpenseModel(
                description = description,
                timestamp = timestamp,
                amount = amount
            )
            val addedId = expenseRepository.addExpense(expense)
            loadExpensesForField(fieldState.value.fieldInfo!!.id)
        }

    }

    fun editExpenses(
        id: Long,
        description: String,
        amount: Double,
        timestamp: Long,
    ) {
        viewModelScope.launch {
            val expense = generateExpenseModel(
                id = id,
                description = description,
                timestamp = timestamp,
                amount = amount
            )
            expenseRepository.updateExpense(expense)
            loadExpensesForField(fieldState.value.fieldInfo!!.id)
        }
    }

    fun removeExpenses(expenseId: Long) {
        viewModelScope.launch {
            val expenseToDelete = fieldState.value.expenses.firstOrNull { it.id == expenseId }
            if (expenseToDelete != null) {
                _fieldInfoState.update {
                    it.copy(
                        expenses = it.expenses.toMutableList().apply {
                            remove(expenseToDelete)
                        }

                    )
                }
                expenseRepository.deleteExpense(expenseToDelete)
            }
        }
    }

    fun removeField(onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (fieldState.value.fieldInfo?.id != null) {
                fieldRepository.deleteFieldById(fieldState.value.fieldInfo!!.id)
                val expenses = expenseRepository.getAllExpensesForField(fieldState.value.fieldInfo!!.id)
                expenses.forEach { expense ->
                    expenseRepository.deleteExpense(expense)
                }
                _fieldInfoState.update {
                    it.copy(
                        fieldInfo = null,
                        expenses = emptyList()
                    )
                }
                onSuccess.invoke()
            }
        }
    }

    private fun generateExpenseModel(
        id: Long = 0,
        description: String,
        timestamp: Long,
        amount: Double
    ): Expense {
        val currentTime = OffsetDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault()).format(
            DateTimeFormatter.ISO_DATE
        )
        return Expense(
            id = id,
            description = description.trim(),
            fieldId = fieldState.value.fieldInfo!!.id,
            date = currentTime,
            amount = amount,
        )
    }

}