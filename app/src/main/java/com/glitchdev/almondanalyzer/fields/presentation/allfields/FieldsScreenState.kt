package com.glitchdev.almondanalyzer.fields.presentation.allfields

import com.glitchdev.almondanalyzer.fields.domain.Field

data class FieldsScreenState(
    val isLoading: Boolean = false,
    val fields: List<Field> = emptyList(),
    val selectedFieldId: Long? = null
)