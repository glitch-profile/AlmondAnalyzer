package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense

import com.glitchdev.almondanalyzer.expenses.data.Expense

data class EditExpenseState(
    val isLoading: Boolean = false,
    val isUploading: Boolean = false,
    val isEditorOpen: Boolean = false,
    val isAddingNewExpense: Boolean = true,
    val editedExpense: Expense? = null,

    val date: String = "",
    val description: String = "",
    val amount: String = ""
)
