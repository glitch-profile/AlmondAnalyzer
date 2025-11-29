package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo

import com.glitchdev.almondanalyzer.expenses.data.Expense
import com.glitchdev.almondanalyzer.fields.domain.Field

data class FieldInfoState(
    val isLoadingField: Boolean = false,
    val isLoadingExpenses: Boolean = false,
    val fieldInfo: Field? = null,
    val expenses: List<Expense> = emptyList()
)
