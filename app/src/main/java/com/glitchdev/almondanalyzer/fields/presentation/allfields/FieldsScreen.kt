package com.glitchdev.almondanalyzer.fields.presentation.allfields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.fields.domain.Field
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.LoadingComponent
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun FieldsScreen(
    state: FieldsScreenState,
    onUpdateFieldsInfo: () -> Unit,
    onSelectField: (fieldId: Long) -> Unit,
    onUnselectField: (fieldId: Long) -> Unit,
    onOpenFieldInfoClicked: (fieldId: Long) -> Unit,
    onAddNewFieldClicked: () -> Unit,
    onEditFieldClicked: (fieldId: Long) -> Unit,
    onDeleteFieldClicked: (fieldId: Long) -> Unit
) {
    LaunchedEffect(null) { onUpdateFieldsInfo.invoke() }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (state.isLoading && state.fields.isEmpty()) {
            LoadingComponent(modifier = Modifier.fillMaxSize())
        } else if (state.fields.isEmpty()) {
            FieldsEmptyComponent(onAddNewFieldClicked = onAddNewFieldClicked)
        } else {
            FieldListComponent(
                fields = state.fields,
                selectedFieldId = state.selectedFieldId,
                onSelectField = onSelectField,
                onUnselectField = onUnselectField,
                onOpenFieldInfoClicked = onOpenFieldInfoClicked,
                onAddNewFieldClicked = onAddNewFieldClicked,
                onEditFieldClicked = onEditFieldClicked,
                onDeleteFieldClicked = onDeleteFieldClicked
            )
        }
    }
}

@Composable
private fun FieldsEmptyComponent(
    onAddNewFieldClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppTheme.size.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.fields_screen_no_fields_title),
                style = AppTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onSurface
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.fields_screen_no_fields_text),
                style = AppTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = AppTheme.colorScheme.onSurface
            )
        }
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClick = onAddNewFieldClicked
        ) {
            Text(stringResource(R.string.fields_screen_add_title_button_label))
        }
    }
}

@Composable
private fun FieldListComponent(
    fields: List<Field>,
    selectedFieldId: Long?,
    onSelectField: (fieldId: Long) -> Unit,
    onUnselectField: (fieldId: Long) -> Unit,
    onOpenFieldInfoClicked: (Long) -> Unit,
    onAddNewFieldClicked: () -> Unit,
    onEditFieldClicked: (Long) -> Unit,
    onDeleteFieldClicked: (Long) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var addButtonHeight by remember { mutableIntStateOf(0) }
        val density = LocalDensity.current

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.size.medium),
            contentPadding = PaddingValues(
                top = AppTheme.size.medium,
                bottom = with(density) { addButtonHeight.toDp() }
            ),
            verticalArrangement = Arrangement.spacedBy(AppTheme.size.small)
        ) {
            items(
                items = fields,
                key = { it.id }
            ) { field ->
                FieldCard(
                    modifier = Modifier
                        .fillMaxWidth(),
                    field = field,
                    isOptionsOpened = field.id == selectedFieldId,
                    onOpenOptions = onSelectField,
                    onCloseOptions = onUnselectField,
                    onOpenFieldClicked = onOpenFieldInfoClicked,
                    onEditFieldClicked = onEditFieldClicked,
                    onDeleFieldClicked = onDeleteFieldClicked,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { size ->
                    addButtonHeight = size.height
                }
                .align(Alignment.BottomStart),
        ) {
            AppButton(
                modifier = Modifier
                    .padding(
                        horizontal = AppTheme.size.medium,
                        vertical = AppTheme.size.medium
                    )
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = onAddNewFieldClicked
            ) {
                Text(stringResource(R.string.fields_screen_add_title_button_label))
            }
        }
    }
}