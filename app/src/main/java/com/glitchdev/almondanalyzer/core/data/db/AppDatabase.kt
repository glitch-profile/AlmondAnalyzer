
package com.example.almondanalyzer.data.expenses

data class Expense(
    val id: Long = 0,
    val date: String,
    val type: String,
    val amount: Double,
    val comment: String? = null,
    val gardenId: Long? = null
)
