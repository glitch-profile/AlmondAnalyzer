package com.glitchdev.almondanalyzer.core.di

import com.glitchdev.almondanalyzer.fields.presentation.allfields.FieldsScreenViewModel
import com.glitchdev.almondanalyzer.fields.presentation.editfield.EditFieldViewModel
import com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.FieldInfoViewModel
import com.glitchdev.almondanalyzer.uploadscreen.presentation.UploadScreenViewModel
import com.glitchdev.almondanalyzer.workscreen.presentation.WorkScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelsModule = module {
    // field screens
    viewModelOf(::FieldsScreenViewModel)
    viewModelOf(::FieldInfoViewModel)
    viewModelOf(::EditFieldViewModel)

    //workscreen
    viewModelOf(::WorkScreenViewModel)

    // upload screen
    viewModelOf(::UploadScreenViewModel)
}