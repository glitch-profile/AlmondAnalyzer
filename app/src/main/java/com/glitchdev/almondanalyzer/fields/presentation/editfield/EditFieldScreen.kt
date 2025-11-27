package com.glitchdev.almondanalyzer.fields.presentation.editfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppFilledTextField
import com.glitchdev.almondanalyzer.ui.components.AppIconButton
import com.glitchdev.almondanalyzer.ui.components.AppTextFieldDefaults
import com.glitchdev.almondanalyzer.ui.components.AppTopBar
import com.glitchdev.almondanalyzer.ui.components.LoadingComponent
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Add
import com.glitchdev.almondanalyzer.ui.icons.svgs.Back
import com.glitchdev.almondanalyzer.ui.icons.svgs.Delete
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault

@Composable
fun EditFieldScreen(
    state: EditFieldScreenState,
    onNameChange: (value: String) -> Unit,
    onVarietyChange: (value: String) -> Unit,
    onCadastralNumberChange: (value: String) -> Unit,
    onPlantingYearChange: (value: String) -> Unit,
    onAddRow: (index: Int) -> Unit,
    onEditRow: (index: Int, value: String) -> Unit,
    onRemoveRow: (index: Int) -> Unit,
    onBackClicked: () -> Unit,
    onUpdateFieldClicked: () -> Unit,
    onAddFieldClicked: () -> Unit,
    onResetToDefaults: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val density = LocalDensity.current
        var bottomMenuHeight by remember { mutableIntStateOf(0) }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AppTopBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    Text(
                        text = if (state.isAddingNewField) stringResource(R.string.add_field_screen_title)
                        else stringResource(R.string.edit_field_screen_title)
                    )
                },
                navigationIcon = {
                    AppIconButton(
                        onClick = onBackClicked,
                        colors = AppButtonDefaults.textButtonColors(
                            contentColor = LocalContentColor.current
                        )
                    ) {
                        Icon(imageVector = AppIcons.Back, contentDescription = null)
                    }
                }
            )
            if (state.isLoading) {
                LoadingComponent(modifier = Modifier.fillMaxSize())
            } else {
                UserInputComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppTheme.size.medium),
                    contentPadding = PaddingValues(
                        top = AppTheme.size.medium,
                        bottom = with (density) { bottomMenuHeight.toDp() }
                    ),
                    state = state,
                    onNameChange = onNameChange,
                    onVarietyChange = onVarietyChange,
                    onCadastralNumberChange = onCadastralNumberChange,
                    onPlantingYearChange = onPlantingYearChange,
                    onAddRow = onAddRow,
                    onEditRow = onEditRow,
                    onRemoveRow = onRemoveRow,
                )
            }
        }
        BottomButtonsComponents(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    bottomMenuHeight = size.height
                }
                .align(Alignment.BottomStart),
            isAddingNewPost = state.isAddingNewField,
            isMainButtonEnabled = !state.isUploading && !state.isLoading,
            isUploadingField = state.isUploading,
            onUpdateFieldClicked = onUpdateFieldClicked,
            onAddNewFieldClicked = onAddFieldClicked,
            onResetToDefaults = onResetToDefaults
        )

    }
}


@Composable
private fun UserInputComponent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    state: EditFieldScreenState,
    onNameChange: (value: String) -> Unit,
    onVarietyChange: (value: String) -> Unit,
    onCadastralNumberChange: (value: String) -> Unit,
    onPlantingYearChange: (value: String) -> Unit,
    onAddRow: (index: Int) -> Unit,
    onEditRow: (index: Int, value: String) -> Unit,
    onRemoveRow: (index: Int) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small),
        ) {
            AppFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.name,
                singleLine = true,
                onValueChange = onNameChange,
                label = { Text(stringResource(R.string.edit_field_name_label)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = AppTextFieldDefaults.colors(
                    unfocusedContainerColor = AppTheme.colorScheme.background.copy(0.6f),
                    focusedContainerColor = AppTheme.colorScheme.background
                )
            )
            AppFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.variety,
                singleLine = true,
                onValueChange = onVarietyChange,
                label = { Text(stringResource(R.string.edit_field_variety_label)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = AppTextFieldDefaults.colors(
                    unfocusedContainerColor = AppTheme.colorScheme.background.copy(0.6f),
                    focusedContainerColor = AppTheme.colorScheme.background
                )
            )
            AppFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.cadastralNumber,
                singleLine = true,
                onValueChange = onCadastralNumberChange,
                label = { Text(stringResource(R.string.edit_field_cadastral_num_label)) },
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = AppTextFieldDefaults.colors(
                    unfocusedContainerColor = AppTheme.colorScheme.background.copy(0.6f),
                    focusedContainerColor = AppTheme.colorScheme.background
                )
            )
            AppFilledTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = state.plantingYear,
                singleLine = true,
                onValueChange = onPlantingYearChange,
                label = { Text(stringResource(R.string.edit_field_year_label)) },
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = AppTextFieldDefaults.colors(
                    unfocusedContainerColor = AppTheme.colorScheme.background.copy(0.6f),
                    focusedContainerColor = AppTheme.colorScheme.background
                )
            )
            SeedlingsRowsComponent(
                modifier = Modifier
                    .fillMaxWidth(),
                seedlingsRows = state.fieldSeedsForRows,
                onAddRow = onAddRow,
                onEditRow = onEditRow,
                onRemoveRow = onRemoveRow,
            )
        }
    }
}

@Composable
private fun SeedlingsRowsComponent(
    modifier: Modifier = Modifier,
    seedlingsRows: List<String>,
    onAddRow: (index: Int) -> Unit,
    onEditRow: (index: Int, value: String) -> Unit,
    onRemoveRow: (index: Int) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.extraSmall)
    ) {
        AppButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onAddRow.invoke(0) },
        ) {
            Icon(
                imageVector = AppIcons.Add,
                contentDescription = null
            )
            Text(stringResource(R.string.edit_field_seedlings_rows_add_button_label))
        }
        seedlingsRows.forEachIndexed { index, count ->
            AppFilledTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                leadingIcon = {
                    Text(
                        modifier = Modifier
                            .height(24.dp),
                        text = "${stringResource(R.string.edit_field_seedlings_rows_row_prefix_label)} #${index + 1}",
                        style = AppTextFieldDefaults.textStyle()
                    )
                },
                value = count,
                singleLine = true,
                onValueChange = { onEditRow.invoke(index, it) },
                label = { Text("0") },
                keyboardOptions = KeyboardOptions(
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = AppTextFieldDefaults.colors(
                    unfocusedContainerColor = AppTheme.colorScheme.background.copy(0.6f),
                    focusedContainerColor = AppTheme.colorScheme.background
                ),
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
                    ) {
                        AppIconButton(
                            modifier = Modifier
                                .size(24.dp),
                            onClick = { onAddRow.invoke(index + 1) },
                            colors = AppButtonDefaults.textButtonColors(
                                contentColor = LocalContentColor.current,
                                disabledContentColor = LocalContentColor.current
                            )
                        ) {
                            Icon(imageVector = AppIcons.Add, contentDescription = null)
                        }
                        AppIconButton(
                            modifier = Modifier
                                .size(24.dp),
                            onClick = { onRemoveRow.invoke(index) },
                            colors = AppButtonDefaults.textButtonColors(
                                contentColor = LocalContentColor.current,
                                disabledContentColor = LocalContentColor.current
                            )
                        ) {
                            Icon(imageVector = AppIcons.Delete, contentDescription = null)
                        }
                    }
                },
                contentPadding = PaddingValues(
                    horizontal = AppTheme.size.medium,
                    vertical = AppTheme.size.medium
                )
            )
        }
    }
}

@Composable
private fun BottomButtonsComponents(
    modifier: Modifier = Modifier,
    isAddingNewPost: Boolean,
    isMainButtonEnabled: Boolean,
    isUploadingField: Boolean,
    onUpdateFieldClicked: () -> Unit,
    onAddNewFieldClicked: () -> Unit,
    onResetToDefaults: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
    ) {
        AppButton(
            modifier = Modifier
                .padding(AppTheme.size.medium)
                .fillMaxWidth()
                .height(48.dp),
            enabled = isMainButtonEnabled,
            onClick = {
                if (isAddingNewPost) onAddNewFieldClicked.invoke()
                else onUpdateFieldClicked.invoke()
            }
        ) {
            Text(
                text = if (isAddingNewPost) stringResource(R.string.edit_field_add_new_field_button_label)
                else stringResource(R.string.edit_field_update_field_button_label)
            )
            AnimatedVisibility(
                visible = isUploadingField,
                enter = expandHorizontally(appSpringDefault()),
                exit = shrinkHorizontally(appSpringDefault())
            ) {
                Column {
                    Spacer(Modifier.width(AppTheme.size.medium))
                    CircularProgressIndicator(
                        color = LocalContentColor.current,
                        strokeWidth = 3.dp
                    )
                }
            }
        }
    }
}