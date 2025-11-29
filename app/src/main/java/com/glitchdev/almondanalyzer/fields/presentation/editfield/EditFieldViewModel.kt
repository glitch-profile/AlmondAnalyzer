package com.glitchdev.almondanalyzer.fields.presentation.editfield

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
import java.time.LocalDate
import java.time.LocalDateTime

class EditFieldViewModel(
    private val fieldsRepository: FieldRepository
): ViewModel() {

    private val _state = MutableStateFlow(EditFieldScreenState())
    val state = _state.asStateFlow()

    fun loadFieldData(fieldId: Long?) {
        if (state.value.isLoading) return
        viewModelScope.launch {
            _state.update { it.copy(isAddingNewField = fieldId == null) }
            if (fieldId != null) {
                _state.update { it.copy(isLoading = true) }
                val field = fieldsRepository.getFieldById(fieldId)
                if (field != null) {
                    _state.update {
                        it.copy(
                            fieldData = field,
                            name = field.name,
                            variety = field.variety,
                            cadastralNumber = field.cadastralNumber,
                            plantingYear = field.plantingYear.toString(),
                            fieldSeedsForRows = decodePlantingScheme(field.plantingScheme)
                        )
                    }
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        isAddingNewField = field == null
                    )
                }
            }
        }
    }

    fun updateName(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun updateVariety(value: String) {
        _state.update { it.copy(variety = value) }
    }

    fun updateCadastralNumber(value: String) {
        _state.update { it.copy(cadastralNumber = value) }
    }

    fun updatePlantingYear(value: String) {
        _state.update { it.copy(plantingYear = value
                .filter { it.isDigit() }
                .take(4)
        ) }
    }

    fun addSeedlingsRow(index: Int) {
        val currentRows = state.value.fieldSeedsForRows
        _state.update {
            it.copy(
                fieldSeedsForRows = currentRows.toMutableList().apply {
                    val nearestSeedlingsCount: String = kotlin.run {
                        val nearestRow = currentRows.getOrNull(index - 1) ?: currentRows.getOrNull(index)
                        nearestRow ?: ""
                    }
                    add(index, nearestSeedlingsCount)
                }
            )
        }
    }

    fun editSeedlingCountForRow(index: Int, value: String) {
        val currentRows = state.value.fieldSeedsForRows
        _state.update {
            it.copy(
                fieldSeedsForRows = currentRows.toMutableList().apply {
                    val count = value.filter { it.isDigit() }
                    set(index, count)
                }
            )
        }
    }

    fun removeSeedlingsRow(index: Int) {
        val currentRows = state.value.fieldSeedsForRows
        _state.update {
            it.copy(
                fieldSeedsForRows = currentRows.toMutableList().apply {
                    removeAt(index)
                }
            )
        }
    }

    fun resetToDefault() {
        val field = state.value.fieldData
        _state.update {
            it.copy(
                name = field?.name ?: "",
                variety = field?.variety ?: "",
                cadastralNumber = field?.cadastralNumber ?: "",
                plantingYear = field?.plantingYear?.toString() ?: LocalDate.now().year.toString(),
                fieldSeedsForRows = if (field != null) decodePlantingScheme(field.plantingScheme)
                    else emptyList()
            )
        }
    }

    fun addField(
        onSuccess: (addedField: Field) -> Unit
    ) {
        if (state.value.isUploading) return
        viewModelScope.launch {
            _state.update { it.copy(isUploading = true) }
            try {
                val fieldToAdd =
                    generateFieldModel()
                val fieldId = fieldsRepository.addField(fieldToAdd)
                val addedField = fieldsRepository.getFieldById(fieldId)
                _state.update {
                    it.copy(
                        fieldData = addedField,
                        isAddingNewField = false
                    )
                }
                if (addedField != null) {
                    onSuccess.invoke(addedField)
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
            } finally {
                _state.update { it.copy(isUploading = false) }
            }
        }
    }

    fun updateField(
        onSuccess: (Field) -> Unit
    ) {
        if (state.value.isUploading) return
        viewModelScope.launch {
            try {
                val fieldToUpdate =
                    generateFieldModel()
                fieldsRepository.updateField(fieldToUpdate)
                val addedField = fieldToUpdate
                _state.update { it.copy(fieldData = addedField) }
                onSuccess.invoke(addedField)
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
            } finally {
                _state.update { it.copy(isUploading = false) }
            }
        }
    }

    private fun generateFieldModel(): Field {
        val formattedName = state.value.name.trim()
        val formattedVariety = state.value.variety.trim()
        val formattedCadastralNum = state.value.cadastralNumber.trim()
        if (formattedName.isEmpty() || formattedVariety.isEmpty() || formattedCadastralNum.isEmpty()) throw EmptyFieldValuesException()
        val currentYear = LocalDateTime.now().year
        val plantingYear = state.value.plantingYear.filter { it.isDigit() }.toIntOrNull() ?: 0
        if (plantingYear > currentYear || plantingYear < 0) throw IncorrectFieldPlantingYearException()
        val fieldSeedsForRows = state.value.fieldSeedsForRows.map { it.filter { it.isDigit() }.toIntOrNull() ?: 0 }
        if (fieldSeedsForRows.any { it < 0 }) throw IncorrectFieldPlantingSchemeException()
        val fieldToAdd = Field(
            id = state.value.fieldData?.id ?: 0,
            name = formattedName,
            variety = formattedVariety,
            cadastralNumber = formattedCadastralNum,
            plantingScheme = generatePlantingScheme(fieldSeedsForRows),
            plantingYear = plantingYear,
            seedlingsCount = fieldSeedsForRows.sum()
        )
        return fieldToAdd
    }

    private fun generatePlantingScheme(seedsForRows: List<Int>): String {
        var plantingSchemeStr = ""
        seedsForRows.forEachIndexed { index, countForRow ->
            repeat(countForRow) { plantingSchemeStr += "-" }
            if (index != seedsForRows.size - 1) plantingSchemeStr += "|"
        }
        return plantingSchemeStr
    }

    private fun decodePlantingScheme(plantingScheme: String): List<String> {
        val rows = plantingScheme.split('|')
        return rows.map { it.length.toString() }
    }

}