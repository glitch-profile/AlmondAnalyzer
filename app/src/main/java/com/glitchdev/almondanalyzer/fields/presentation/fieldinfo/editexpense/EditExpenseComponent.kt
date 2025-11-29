package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo.editexpense

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.components.AppBottomSheet
import com.glitchdev.almondanalyzer.ui.components.AppBottomSheetDefaults
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppFilledTextField
import com.glitchdev.almondanalyzer.ui.components.AppIconButton
import com.glitchdev.almondanalyzer.ui.components.AppTextFieldDefaults
import com.glitchdev.almondanalyzer.ui.components.AppTopBar
import com.glitchdev.almondanalyzer.ui.components.LoadingComponent
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Clear
import com.glitchdev.almondanalyzer.ui.icons.svgs.Delete
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditExpenseComponent(
    modifier: Modifier = Modifier,
    state: EditExpenseState,
    onDismissRequest: () -> Unit,
    onUpdateDescription: (value: String) -> Unit,
    onUpdateDate: (value: String) -> Unit,
    onUpdateAmount: (value: String) -> Unit,
    onAddExpenseClicked: () -> Unit,
    onUpdateExpenseClicked: () -> Unit,
    onDeleteExpenseClicked: () -> Unit
) {
    val editorSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    if (state.isEditorOpen) {
        AppBottomSheet(
            modifier = Modifier
                .fillMaxWidth(),
            sheetState = editorSheetState,
            colors = AppBottomSheetDefaults.colors(
                containerColor = AppTheme.colorScheme.surface
            ),
            onDismissRequest = onDismissRequest
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(appSpringDefault())
            ) {
                AppTopBar(
                    title = {
                        Text(stringResource(R.string.edit_expense_add_title))
                    },
                    navigationIcon = {
                        AppIconButton(
                            onClick = onDismissRequest,
                            colors = AppButtonDefaults.textButtonColors(
                                contentColor = LocalContentColor.current
                            )
                        ) {
                            Icon(
                                imageVector = AppIcons.Clear,
                                contentDescription = null
                            )
                        }
                    }
                )

                if (state.isLoading) {
                    LoadingComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = AppTheme.size.medium)
                    )
                } else {
                    Spacer(Modifier.height(AppTheme.size.medium))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = AppTheme.size.medium),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
                    ) {
                        AppFilledTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = state.description,
                            singleLine = true,
                            onValueChange = onUpdateDescription,
                            label = { Text(stringResource(R.string.edit_expense_description_label)) },
                            keyboardOptions = KeyboardOptions(
                                autoCorrectEnabled = true,
                                capitalization = KeyboardCapitalization.Sentences,
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
                            value = state.amount,
                            singleLine = true,
                            onValueChange = onUpdateAmount,
                            label = { Text(stringResource(R.string.edit_expense_amount_label)) },
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
                            value = state.date,
                            singleLine = true,
                            onValueChange = onUpdateDate,
                            label = { Text(stringResource(R.string.edit_expense_date_label)) },
                            keyboardOptions = KeyboardOptions(
                                autoCorrectEnabled = true,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = { if (state.isAddingNewExpense) onAddExpenseClicked else onUpdateExpenseClicked }
                            ),
                            colors = AppTextFieldDefaults.colors(
                                unfocusedContainerColor = AppTheme.colorScheme.background.copy(0.6f),
                                focusedContainerColor = AppTheme.colorScheme.background
                            )
                        )
                    }
                    Spacer(Modifier.height(AppTheme.size.medium))
                    BottomButtonsComponents(
                        isAddingExpense = state.isAddingNewExpense,
                        isMainButtonEnabled = !state.isUploading,
                        isUploading = state.isUploading,
                        onUpdateClicked = onUpdateExpenseClicked,
                        onAddClicked = onAddExpenseClicked,
                        onDeleteClicked = onDeleteExpenseClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomButtonsComponents(
    modifier: Modifier = Modifier,
    isAddingExpense: Boolean,
    isMainButtonEnabled: Boolean,
    isUploading: Boolean,
    onUpdateClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.size.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
        ) {
            if (!isAddingExpense) {
                AppIconButton(
                    modifier = Modifier
                        .size(48.dp),
                    onClick = onDeleteClicked,
                    shape = AppButtonDefaults.shape(),
                    colors = AppButtonDefaults.textButtonColors(
                        contentColor = AppTheme.colorScheme.notation
                    )
                ) {
                    Icon(
                        imageVector = AppIcons.Delete,
                        contentDescription = null
                    )
                }
            }
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = isMainButtonEnabled,
                onClick = {
                    if (isAddingExpense) onAddClicked.invoke()
                    else onUpdateClicked.invoke()
                }
            ) {
                Text(
                    text = if (isAddingExpense) stringResource(R.string.edit_expense_add_button_label)
                    else stringResource(R.string.edit_expense_update_button_label)
                )
                AnimatedVisibility(
                    visible = isUploading,
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
}