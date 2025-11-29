package com.glitchdev.almondanalyzer.core.di

import androidx.room.Room
import com.glitchdev.almondanalyzer.core.data.db.AppDatabase
import com.glitchdev.almondanalyzer.expenses.data.ExpenseRepositoryImpl
import com.glitchdev.almondanalyzer.expenses.domain.ExpenseRepository
import com.glitchdev.almondanalyzer.fields.data.FieldRepositoryImpl
import com.glitchdev.almondanalyzer.fields.domain.FieldRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    // DATABASE
    single<AppDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    // DAO
    single { get<AppDatabase>().fieldDao() }
    single { get<AppDatabase>().expenseDao() }

    // Repository
    single<FieldRepository> { FieldRepositoryImpl(get()) }
    single<ExpenseRepository> { ExpenseRepositoryImpl(get()) }
}