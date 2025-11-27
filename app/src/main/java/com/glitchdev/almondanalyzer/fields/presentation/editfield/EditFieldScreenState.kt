package com.glitchdev.almondanalyzer.fields.presentation.editfield

import com.glitchdev.almondanalyzer.fields.domain.Field
import java.time.LocalDate

data class EditFieldScreenState(
    val isLoading: Boolean = false,
    val isUploading: Boolean = false,
    val isAddingNewField: Boolean = true,
    val fieldData: Field? = null,

    val name: String = "",
    val variety: String = "",
    val cadastralNumber: String = "",
    val fieldSeedsForRows: List<String> = emptyList(),
    val plantingYear: String = LocalDate.now().year.toString()

)