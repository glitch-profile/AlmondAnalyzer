package com.glitchdev.almondanalyzer.core.di

import com.glitchdev.almondanalyzer.core.data.db.AppDatabase
import com.glitchdev.almondanalyzer.fields.data.FieldRepositoryImpl
import com.glitchdev.almondanalyzer.fields.domain.FieldRepository
import org.koin.dsl.module

val fieldModule = module {

    // DAO
    single { get<AppDatabase>().fieldDao() }

    // Repository
    single<FieldRepository> { FieldRepositoryImpl(get()) }
}