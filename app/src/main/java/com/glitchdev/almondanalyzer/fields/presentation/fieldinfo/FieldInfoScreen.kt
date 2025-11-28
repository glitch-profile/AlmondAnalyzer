package com.glitchdev.almondanalyzer.fields.presentation.fieldinfo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.expenses.data.Expense
import com.glitchdev.almondanalyzer.fields.domain.Field
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppIconButton
import com.glitchdev.almondanalyzer.ui.components.AppTopBar
import com.glitchdev.almondanalyzer.ui.components.LoadingComponent
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Back
import com.glitchdev.almondanalyzer.ui.icons.svgs.Calendar
import com.glitchdev.almondanalyzer.ui.icons.svgs.Hashtag
import com.glitchdev.almondanalyzer.ui.icons.svgs.Name
import com.glitchdev.almondanalyzer.ui.icons.svgs.Tree
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault
import java.util.Locale

@Composable
fun FieldInfoScreen(
    state: FieldInfoState,
    onBackClicked: () -> Unit,
    onReloadClicked: () -> Unit,
    onOpenExpenseEditor: () -> Unit,
    onCloseExpenseEditor: () -> Unit,
    onAddExpense: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppTopBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = {
                Text(
                    text = stringResource(R.string.field_details_screen_title)
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
            },
            actions = {

                AppIconButton(
                    onClick = onReloadClicked,
                    colors = AppButtonDefaults.textButtonColors(
                        contentColor = LocalContentColor.current
                    )
                ) {
                    Icon(imageVector = AppIcons.Back, contentDescription = null)
                }
            }
        )
        if (state.isLoadingField && state.fieldInfo == null) {
            LoadingComponent(Modifier.fillMaxSize())
        } else if (state.fieldInfo != null) {
            FieldInfoComponent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppTheme.size.medium),
                contentPadding = PaddingValues(vertical = AppTheme.size.medium),
                fieldInfo = state.fieldInfo,
                isLoadingExpenses = state.isLoadingExpenses,
                expenses = state.expenses,
            )
        } else {
            // ERROR SCREEN
        }
    }
}


@Composable
private fun FieldInfoComponent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    fieldInfo: Field,
    isLoadingExpenses: Boolean,
    expenses: List<Expense>
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            Text(
                text = stringResource(R.string.field_details_general_section_title),
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colorScheme.primary
            )
            SingleInfoComponent(
                modifier = Modifier.fillMaxWidth(),
                image = AppIcons.Name,
                title = stringResource(R.string.field_details_field_name_title),
                value = fieldInfo.name,
            )
            SingleInfoComponent(
                modifier = Modifier.fillMaxWidth(),
                image = AppIcons.Tree,
                title = stringResource(R.string.field_details_field_variety_title),
                value = fieldInfo.variety,
            )
            SingleInfoComponent(
                modifier = Modifier.fillMaxWidth(),
                image = AppIcons.Calendar,
                title = stringResource(R.string.field_details_field_year_title),
                value = fieldInfo.plantingYear.toString(),
            )
            SingleInfoComponent(
                modifier = Modifier.fillMaxWidth(),
                image = AppIcons.Hashtag,
                title = stringResource(R.string.field_details_field_cadastral_num_title),
                value = fieldInfo.cadastralNumber,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            val rowsScheme = remember {
                val rows = fieldInfo.plantingScheme.split('|')
                rows.map { it.length }
            }
            Text(
                text = stringResource(R.string.field_details_field_seedlings_rows_section_title),
                style = AppTheme.typography.titleMedium,
                color = AppTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                val normalCorner = AppTheme.size.small
                val smallCorner = AppTheme.size.extraSmall
                rowsScheme.forEachIndexed { index, count ->
                    val shape = if (index == 0) RoundedCornerShape(normalCorner, normalCorner, smallCorner, smallCorner)
                        else RoundedCornerShape(smallCorner)
                    RowSeedlingCountComponent(
                        title = "${stringResource(R.string.field_details_field_seedlings_row_title)} #${index + 1}",
                        count = count,
                        shape = shape
                    )
                }
                RowSeedlingCountComponent(
                    title = stringResource(R.string.field_details_field_seedlings_row_total_title),
                    count = fieldInfo.seedlingsCount,
                    shape = RoundedCornerShape(smallCorner, smallCorner, normalCorner, normalCorner)
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                AnimatedVisibility(
                    visible = isLoadingExpenses,
                    enter = expandHorizontally(appSpringDefault()),
                    exit = shrinkHorizontally(appSpringDefault())
                ) {
                    Row {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(16.dp),
                            color = AppTheme.colorScheme.primary,
                            strokeWidth = 3.dp
                        )
                        Spacer(Modifier.width(AppTheme.size.medium))
                    }
                }
                Text(
                    text = stringResource(R.string.field_details_field_expenses_section_title),
                    style = AppTheme.typography.titleMedium,
                    color = AppTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                val normalCorner = AppTheme.size.small
                val smallCorner = AppTheme.size.extraSmall
                expenses.forEachIndexed { index, expense ->
                    val shape = if (index == 0) RoundedCornerShape(normalCorner, normalCorner, smallCorner, smallCorner)
                    else RoundedCornerShape(smallCorner)
                    ExpenseComponent(
                        expense = expense,
                        shape = shape
                    )
                }

            }
        }
    }
}

@Composable
private fun RowSeedlingCountComponent(
    title: String,
    count: Int,
    shape: Shape
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colorScheme.background,
                shape = shape
            )
            .padding(
                horizontal = AppTheme.size.medium,
                vertical = AppTheme.size.small
            ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = AppTheme.colorScheme.onSurfaceVariant,
            style = AppTheme.typography.bodyMedium
        )
        Text(
            text = pluralStringResource(
                id = R.plurals.field_details_field_seedling_count_text,
                count = count,
                count
            ),
            color = AppTheme.colorScheme.onSurface,
            style = AppTheme.typography.titleSmall
        )
    }
}

@Composable
private fun ExpenseComponent(
    expense: Expense,
    shape: Shape
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colorScheme.background,
                shape = shape
            )
            .padding(
                horizontal = AppTheme.size.medium,
                vertical = AppTheme.size.small
            )
    ) {
        if (expense.description.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = expense.description,
                    style = AppTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = AppTheme.colorScheme.onSurface
                )
//                Text(
//                    text = "Описание",
//                    style = AppTheme.typography.bodyMedium,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    color = AppTheme.colorScheme.onSurfaceVariant
//                )
            }
        }
        Spacer(Modifier.height(AppTheme.size.small))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(2f)
            ) {
                Text(
                    text = String.format(Locale.getDefault(), "%.2f", expense.amount),
                    style = AppTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = AppTheme.colorScheme.onSurface
                )
//                Text(
//                    text = "Сумма",
//                    style = AppTheme.typography.bodyMedium,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    color = AppTheme.colorScheme.onSurfaceVariant
//                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = expense.date,
                    style = AppTheme.typography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = AppTheme.colorScheme.onSurface
                )
//                Text(
//                    text = "Дата списания",
//                    style = AppTheme.typography.bodyMedium,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis,
//                    color = AppTheme.colorScheme.onSurfaceVariant
//                )
            }
        }
    }
}

@Composable
private fun SingleInfoComponent(
    modifier: Modifier = Modifier,
    image: ImageVector,
    title: String,
    value: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = image,
            contentDescription = null
        )
        Spacer(Modifier.width(AppTheme.size.small))
        Column {
            Text(
                text = value,
                style = AppTheme.typography.titleSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AppTheme.colorScheme.onSurface
            )
            Text(
                text = title,
                style = AppTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AppTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}