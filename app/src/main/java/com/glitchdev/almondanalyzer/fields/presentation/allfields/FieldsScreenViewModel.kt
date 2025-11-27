package com.glitchdev.almondanalyzer.fields.presentation.allfields

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.fields.domain.Field
import com.glitchdev.almondanalyzer.fields.domain.FieldRepository
import com.glitchdev.almondanalyzer.fields.presentation.exceptions.EmptyFieldValuesException
import com.glitchdev.almondanalyzer.fields.presentation.exceptions.FieldBaseException
import com.glitchdev.almondanalyzer.fields.presentation.exceptions.IncorrectFieldPlantingSchemeException
import com.glitchdev.almondanalyzer.fields.presentation.exceptions.IncorrectFieldPlantingYearException
import com.glitchdev.almondanalyzer.ui.components.notification.NotificationAction
import com.glitchdev.almondanalyzer.ui.components.notification.NotificationController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class FieldsScreenViewModel(
    private val fieldsRepository: FieldRepository,
): ViewModel() {

    private val _fieldsState = MutableStateFlow(FieldsScreenState())
    val fieldsState = _fieldsState.asStateFlow()

    fun loadFields() {
        if (fieldsState.value.isLoading) return
        viewModelScope.launch {
            _fieldsState.update { it.copy(isLoading = true) }
            val fieldsData = fieldsRepository.getAllFields()
            _fieldsState.update {
                it.copy(
                    isLoading = false,
                    fields = fieldsData
                )
            }
        }
    }

    fun selectField(fieldId: Long?) {
        viewModelScope.launch {
            if (fieldsState.value.selectedFieldId != fieldId) {
                _fieldsState.update { it.copy(selectedFieldId = fieldId) }
            }
        }
    }

    fun addField(
        name: String,
        variety: String,
        cadastralNumber: String,
        fieldSeedsForRow: List<Int>,
        plantingYear: Int,
        onSuccess: (addedField: Field) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val fieldToAdd =
                    generateFieldModel(
                        name = name,
                        variety = variety,
                        cadastralNumber = cadastralNumber,
                        plantingYear = plantingYear,
                        fieldSeedsForRow = fieldSeedsForRow
                    )
                val fieldId = fieldsRepository.addField(fieldToAdd)
                val addedField = fieldsRepository.getFieldById(fieldId)
                if (addedField != null) {
                    onSuccess.invoke(addedField)
                    loadFields()
                }
            } catch (e: FieldBaseException) {
                when (e) {
                    is EmptyFieldValuesException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_empty_fields_title,
                                messageRes = R.string.edit_field_error_empty_fields_text
                            )
                        )
                    }
                    is IncorrectFieldPlantingYearException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_incorrect_year_title,
                                messageRes = R.string.edit_field_error_incorrect_year_text
                            )
                        )
                    }
                    is IncorrectFieldPlantingSchemeException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_incorrect_scheme_title,
                                messageRes = R.string.edit_field_error_incorrect_scheme_text
                            )
                        )
                    }
                    else -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_unknown_title,
                                messageRes = R.string.edit_field_error_unknown_text
                            )
                        )
                    }
                }
            }
        }
    }

    fun updateField(
        id: Long,
        name: String,
        variety: String,
        cadastralNumber: String,
        fieldSeedsForRow: List<Int>,
        plantingYear: Int,
        onSuccess: (addedField: Field) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val fieldToUpdate =
                    generateFieldModel(
                        id = id,
                        name = name,
                        variety = variety,
                        cadastralNumber = cadastralNumber,
                        plantingYear = plantingYear,
                        fieldSeedsForRow = fieldSeedsForRow
                    )
                fieldsRepository.updateField(fieldToUpdate)
                val addedField = fieldToUpdate
                onSuccess.invoke(addedField)
                loadFields()
            } catch (e: FieldBaseException) {
                when (e) {
                    is EmptyFieldValuesException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_empty_fields_title,
                                messageRes = R.string.edit_field_error_empty_fields_text
                            )
                        )
                    }
                    is IncorrectFieldPlantingYearException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_incorrect_year_title,
                                messageRes = R.string.edit_field_error_incorrect_year_text
                            )
                        )
                    }
                    is IncorrectFieldPlantingSchemeException -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_incorrect_scheme_title,
                                messageRes = R.string.edit_field_error_incorrect_scheme_text
                            )
                        )
                    }
                    else -> {
                        NotificationController.sendEvent(
                            NotificationAction(
                                titleRes = R.string.edit_field_error_unknown_title,
                                messageRes = R.string.edit_field_error_unknown_text
                            )
                        )
                    }
                }
            }
        }
    }

    fun deleteField(
        fieldId: Long,
    ) {
        viewModelScope.launch {
            fieldsRepository.deleteFieldById(fieldId)
            _fieldsState.update {
                it.copy(
                    fields = fieldsState.value.fields.toMutableList().apply {
                        val index = this.indexOfFirst { it.id == fieldId }
                        if (index != -1) this.removeAt(index)
                    }
                )
            }
        }
    }

    private fun generateFieldModel(
        id: Long = 0,
        name: String,
        variety: String,
        cadastralNumber: String,
        plantingYear: Int,
        fieldSeedsForRow: List<Int>
    ): Field {
        val formattedName = name.trim()
        val formattedVariety = variety.trim()
        val formattedCadastralNum = cadastralNumber.trim()
        if (formattedName.isEmpty() || formattedVariety.isEmpty() || formattedCadastralNum.isEmpty()) throw EmptyFieldValuesException()
        val currentYear = LocalDateTime.now().year
        if (plantingYear > currentYear || plantingYear < 0) throw IncorrectFieldPlantingYearException()
        if (fieldSeedsForRow.any { it < 0 }) throw IncorrectFieldPlantingSchemeException()
        val fieldToAdd = Field(
            id = id,
            name = formattedName,
            variety = formattedVariety,
            cadastralNumber = formattedCadastralNum,
            plantingScheme = generatePlantingScheme(fieldSeedsForRow),
            plantingYear = plantingYear,
            seedlingsCount = fieldSeedsForRow.sum()
        )
        return fieldToAdd
    }

    private fun generatePlantingScheme(seedsForRows: List<Int>): String {
        var plantingSchemeStr = ""
        seedsForRows.forEach { countForRow ->
            repeat(countForRow) { plantingSchemeStr += "-" }
            plantingSchemeStr += "|"
        }
        return plantingSchemeStr
    }

}