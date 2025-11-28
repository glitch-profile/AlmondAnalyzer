package com.glitchdev.almondanalyzer.fields.presentation.allfields

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.fields.domain.Field
import com.glitchdev.almondanalyzer.ui.components.AppButton
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppTextButton
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Calendar
import com.glitchdev.almondanalyzer.ui.icons.svgs.Flowerpot
import com.glitchdev.almondanalyzer.ui.icons.svgs.Hashtag
import com.glitchdev.almondanalyzer.ui.icons.svgs.Name
import com.glitchdev.almondanalyzer.ui.icons.svgs.Tree
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault

@Composable
fun FieldCard(
    modifier: Modifier = Modifier,
    field: Field,
    isOptionsOpened: Boolean,
    onOpenOptions: (filedId: Long) -> Unit,
    onCloseOptions: (filedId: Long) -> Unit,
    onOpenFieldClicked: (fieldId: Long) -> Unit,
    onEditFieldClicked: (fieldId: Long) -> Unit,
    onDeleFieldClicked: (fieldId: Long) -> Unit
) {
    Box(
        modifier = modifier
            .clip(AppTheme.shape.small)
            .clipToBounds()
            .background(AppTheme.colorScheme.background)
            .clickable(
                onClick = {
                    if (isOptionsOpened) onCloseOptions.invoke(field.id)
                    else onOpenOptions.invoke(field.id)
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppTheme.size.medium,
                    vertical = AppTheme.size.small
                )
        ) {
            TextGroup(
                modifier = Modifier
                    .fillMaxWidth(),
                image = AppIcons.Name,
                value = field.name,
                description = stringResource(R.string.field_card_field_name_title)
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = AppTheme.size.small),
                color = AppTheme.colorScheme.outline
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium),
            ) {
                TextGroup(
                    modifier = Modifier
                        .weight(1.5f),
                    image = AppIcons.Tree,
                    value = field.variety,
                    description = stringResource(R.string.field_card_field_variety_title)
                )
                Spacer(Modifier.width(AppTheme.size.medium))
                TextGroup(
                    modifier = Modifier
                        .weight(1f),
                    image = AppIcons.Calendar,
                    value = field.plantingYear.toString(),
                    description = stringResource(R.string.field_card_field_year_title)
                )
            }
            Spacer(Modifier.height(AppTheme.size.medium))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
            ) {
                TextGroup(
                    modifier = Modifier
                        .weight(1.5f),
                    image = AppIcons.Hashtag,
                    value = field.cadastralNumber,
                    description = stringResource(R.string.field_card_field_cadastral_num_title)
                )
                Spacer(Modifier.width(AppTheme.size.medium))
                TextGroup(
                    modifier = Modifier
                        .weight(1f),
                    image = AppIcons.Flowerpot,
                    value = field.seedlingsCount.toString(),
                    description = stringResource(R.string.field_card_field_seedlings_count_title)
                )
            }
        }
        var optionsMenuOffset by remember { mutableFloatStateOf(200f) }
        val offset by animateFloatAsState(
            targetValue = if (isOptionsOpened) 0f else optionsMenuOffset,
            animationSpec = appSpringDefault()
        )
        if (optionsMenuOffset != offset) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .onSizeChanged { size ->
                        optionsMenuOffset = size.height.toFloat()
                    }
                    .graphicsLayer {
                        translationY = offset
                    },
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(AppTheme.size.extraLarge)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    AppTheme.colorScheme.background.copy(0f),
                                    AppTheme.colorScheme.background
                                )
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppTheme.colorScheme.background)
                        .padding(AppTheme.size.medium),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium)
                ) {
                    var isSureAboutDelete by remember { mutableStateOf(false) }
                    AppTextButton(
                        modifier = Modifier
                            .height(AppButtonDefaults.minCompactButtonHeight)
                            .animateContentSize(appSpringDefault()),
                        shape = AppTheme.shape.extraSmall,
                        colors = AppButtonDefaults.textButtonColors(
                            contentColor = AppTheme.colorScheme.notation
                        ),
                        onClick = {
                            if (!isSureAboutDelete) isSureAboutDelete = true
                            else onDeleFieldClicked.invoke(field.id)
                        }
                    ) {
                        Text(
                            text = if (isSureAboutDelete) stringResource(R.string.field_card_field_sure_delete_field_button_label)
                            else stringResource(R.string.field_card_field_delete_field_button_label)
                        )
                    }
                    AppTextButton(
                        modifier = Modifier
                            .height(AppButtonDefaults.minCompactButtonHeight),
                        shape = AppTheme.shape.extraSmall,
                        onClick = { onEditFieldClicked.invoke(field.id) }
                    ) {
                        Text(text = stringResource(R.string.field_card_field_edit_field_button_label))
                    }
                    AppButton(
                        modifier = Modifier
                            .height(AppButtonDefaults.minCompactButtonHeight)
                            .weight(1f),
                        shape = AppTheme.shape.extraSmall,
                        onClick = { onOpenFieldClicked.invoke(field.id) },
                        contentPadding = AppButtonDefaults.filledButtonWithIconPadding
                    ) {
                        Text(
                            text = stringResource(R.string.field_card_field_open_field_button_label)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TextGroup(
    modifier: Modifier = Modifier,
    image: ImageVector,
    value: String,
    description: String
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
                text = description,
                style = AppTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AppTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}