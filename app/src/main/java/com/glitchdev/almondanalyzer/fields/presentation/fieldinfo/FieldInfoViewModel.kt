package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.expenses.data.Expense
import com.glitchdev.almondanalyzer.expenses.domain.ExpenseRepository
import com.glitchdev.almondanalyzer.fields.domain.FieldRepository
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense.EditExpenseState
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense.ExpenseBaseException
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense.ExpenseDateNotAvailableException
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense.IncorrectExpenseAmountException
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense.IncorrectExpenseDateException
import com.glitchdev.almondanalyzer.ui.components.notification.NotificationAction
import com.glitchdev.almondanalyzer.ui.components.notification.NotificationController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class FieldInfoViewModel(
    private val fieldRepository: FieldRepository,
    private val expenseRepository: ExpenseRepository
): ViewModel() {

    private val _fieldInfoState = MutableStateFlow(FieldInfoState())
    val fieldState = _fieldInfoState.asStateFlow()

    private val _editExpenseState = MutableStateFlow(EditExpenseState())
    val expenseEditorState = _editExpenseState.asStateFlow()

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
            val expenses = expenseRepository.getAllExpensesForField(fieldId)
//            val expenses = listOf(
//                Expense(
//                    id = 1,
//                    date = "12-11-2025",
//                    fieldId = 111,
//                    description = "Первые затраты",
//                    amount = 12000.00
//                ),
//                Expense(
//                    id = 1,
//                    date = "12-11-2025",
//                    fieldId = 111,
//                    description = "Вторые затраты",
//                    amount = 23718.56
//                ), Expense(
//                    id = 1,
//                    date = "12-11-2025",
//                    fieldId = 111,
//                    description = "Купили первые саженцы",
//                    amount = 960000.0
//                ),
//                Expense(
//                    id = 1,
//                    date = "12-11-2025",
//                    fieldId = 111,
//                    description = "Обрамление грядок",
//                    amount = 1000.0
//                )
//            )
            _fieldInfoState.update {
                it.copy(
                    expenses = expenses,
                    isLoadingExpenses = false
                )
            }
        }
    }

    private suspend fun getExpenseById(fieldId: Long, expenseId: Long): Expense? {
        val expensesForField = expenseRepository.getAllExpensesForField(fieldId)
        val expense = expensesForField.firstOrNull { it.id == expenseId }
        return expense
    }

    fun openExpenseEditor(expenseId: Long?) {
        if (fieldState.value.fieldInfo?.id == null) return
        viewModelScope.launch {
            _editExpenseState.update {
                it.copy(
                    isEditorOpen = true,
                    editedExpense = null,
                    isAddingNewExpense = expenseId == null,
                    isLoading = expenseId != null,
                    description = "",
                    amount = "",
                    date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                )
            }
            if (expenseId != null) {
                _editExpenseState.update { it.copy(isLoading = true) }
                val expenseToEdit = getExpenseById(
                    fieldId = fieldState.value.fieldInfo!!.id,
                    expenseId = expenseId
                )
                _editExpenseState.update {
                    if (expenseToEdit != null) {
                        it.copy(
                            isLoading = false,
                            editedExpense = expenseToEdit,
                            description = expenseToEdit.description,
                            date = expenseToEdit.date,
                            amount = String.format(Locale.getDefault(), "%.2f", expenseToEdit.amount)
                                .replace(',', '.')
                        )
                    } else {
                        it.copy(
                            isLoading = false,
                            isAddingNewExpense = true
                        )
                    }
                }

            }
        }
    }

    fun closeExpenseEditor() { _editExpenseState.update { it.copy(isEditorOpen = false) } }

    fun updateExpenseDescription(value: String) { _editExpenseState.update { it.copy(description = value) } }

    fun updateExpenseAmount(value: String) {
        _editExpenseState.update {
            it.copy(amount = value.filter { it.isDigit() || listOf('.', ',').contains(it) }.replace(',', '.'))
        }
    }

    fun updateExpenseDate(value: String) {
        _editExpenseState.update {
            it.copy(date = value.filter { it.isDigit() || it == '.' })
        }
    }

    fun addExpenses() {
        viewModelScope.launch {
            try {
                val expense = generateExpenseModel()
                expenseRepository.addExpense(expense)
                loadExpensesForField(fieldState.value.fieldInfo!!.id)
                closeExpenseEditor()
            } catch (e: ExpenseBaseException) {
                when (e) {
                    is IncorrectExpenseAmountException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_incorrect_amount_title,
                                messageRes = R.string.edit_expense_error_incorrect_amount_text
                            )
                        )
                    }
                    is IncorrectExpenseDateException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_incorrect_date_title,
                                messageRes = R.string.edit_expense_error_incorrect_date_text
                            )
                        )
                    }
                    is ExpenseDateNotAvailableException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_date_out_of_range_title,
                                messageRes = R.string.edit_expense_error_date_out_of_range_text
                            )
                        )
                    }
                    else -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_unknown_title,
                                messageRes = R.string.edit_expense_error_unknown_text
                            )
                        )
                    }
                }
            }
        }
    }

    fun editExpenses() {
        viewModelScope.launch {
            try {
                val expense = generateExpenseModel()
                expenseRepository.updateExpense(expense)
                loadExpensesForField(fieldState.value.fieldInfo!!.id)
                closeExpenseEditor()
            } catch (e: ExpenseBaseException) {
                when (e) {
                    is IncorrectExpenseAmountException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_incorrect_amount_title,
                                messageRes = R.string.edit_expense_error_incorrect_amount_text
                            )
                        )
                    }
                    is IncorrectExpenseDateException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_incorrect_date_title,
                                messageRes = R.string.edit_expense_error_incorrect_date_text
                            )
                        )
                    }
                    is ExpenseDateNotAvailableException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_date_out_of_range_title,
                                messageRes = R.string.edit_expense_error_date_out_of_range_text
                            )
                        )
                    }
                    else -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_expense_error_unknown_title,
                                messageRes = R.string.edit_expense_error_unknown_text
                            )
                        )
                    }
                }
            }
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
                closeExpenseEditor()
            }
        }
    }

    private fun generateExpenseModel(): Expense {
        val formattedDescription = expenseEditorState.value.description.trim()
        val date = expenseEditorState.value.date.trim()
        val parsedDate = runCatching {
            if (date.isEmpty()) LocalDate.now() else LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }
        val formattedDate = parsedDate.getOrNull() ?: throw IncorrectExpenseDateException()
        val formattedAmount = expenseEditorState.value.amount.toFloatOrNull() ?: 0f
        val currentDate = LocalDate.now()
        if (date.isEmpty()) throw IncorrectExpenseDateException()
        if (formattedAmount < 0f) throw IncorrectExpenseAmountException()
        if (formattedDate.isAfter(currentDate)) throw ExpenseDateNotAvailableException()
        return Expense(
            id = expenseEditorState.value.editedExpense?.id ?: 0,
            description = formattedDescription,
            fieldId = fieldState.value.fieldInfo!!.id,
            date = date,
            amount = formattedAmount.toDouble(),
        )
    }

}