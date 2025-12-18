package com.glitchdev.almondanalyzer.workscreen.presentation

import androidx.lifecycle.ViewModel
import com.glitchdev.almondanalyzer.expenses.domain.ExpenseRepository

class WorkScreenViewModel(
    private val fieldsRepository: ExpenseRepository,
    private val expenseRepository: ExpenseRepository
): ViewModel() {

    // вся логика экрана должна быть здесь

}