package com.glitchdev.almondanalyzer.core.di

import com.glitchdev.almondanalyzer.uploadscreen.presentation.UploadScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    // upload
    viewModelOf(::UploadScreenViewModel)
}