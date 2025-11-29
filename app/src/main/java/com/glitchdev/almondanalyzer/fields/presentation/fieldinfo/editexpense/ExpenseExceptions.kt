package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense

open class ExpenseBaseException: Exception()

class EmptyExpenseValuesException: ExpenseBaseException()
class IncorrectExpenseDescriptionException: ExpenseBaseException()
class IncorrectExpenseDateException: ExpenseBaseException()
class ExpenseDateNotAvailableException: ExpenseBaseException()
class IncorrectExpenseAmountException: ExpenseBaseException()