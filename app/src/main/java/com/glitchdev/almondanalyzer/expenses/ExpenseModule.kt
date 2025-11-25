package com.glitchdev.almondanalyzer.expenses.di

import com.glitchdev.almondanalyzer.core.data.db.AppDatabase
import com.glitchdev.almondanalyzer.expenses.data.ExpenseRepositoryImpl
import com.glitchdev.almondanalyzer.expenses.domain.ExpenseRepository
import org.koin.dsl.module

val expenseModule = module {
    single { get<AppDatabase>().expenseDao() }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get()) }
}