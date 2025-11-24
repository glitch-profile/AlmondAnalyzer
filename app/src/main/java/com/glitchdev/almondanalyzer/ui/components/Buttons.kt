package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = AppButtonDefaults.shape(),
    colors: ButtonColors = AppButtonDefaults.filledButtonColors(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = AppButtonDefaults.filledButtonPadding,
    content: @Composable (RowScope.() -> Unit)

) {
    val containerColor by animateColorAsState(
        if (enabled) colors.containerColor else colors.disabledContainerColor
    )
    val contentColor by animateColorAsState(
        if (enabled) colors.contentColor else colors.disabledContentColor
    )
    val innerInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .defaultMinSize(
                minHeight = AppButtonDefaults.minButtonHeight,
                minWidth = AppButtonDefaults.minButtonWidth
            )
            .clip(shape)
            .clipToBounds()
            .background(containerColor)
            .run {
                if (border != null) border(border, shape)
                else this
            }
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = innerInteractionSource,
                indication = ripple(color = colors.contentColor)
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides AppButtonDefaults.getDefaultTextStyle()
        ) {
            content.invoke(this)
        }
    }
}

@Composable
fun AppTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = AppButtonDefaults.shape(),
    colors: ButtonColors = AppButtonDefaults.textButtonColors(),
    border: BorderStroke? = null,
    interactionSource: MutableInteractionSource? = null,
    contentPadding: PaddingValues = AppButtonDefaults.textButtonPadding,
    content: @Composable (RowScope.() -> Unit)

) {
    val containerColor by animateColorAsState(
        if (enabled) colors.containerColor else colors.disabledContainerColor
    )
    val contentColor by animateColorAsState(
        if (enabled) colors.contentColor else colors.disabledContentColor
    )
    val innerInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .defaultMinSize(
                minHeight = AppButtonDefaults.minButtonHeight,
                minWidth = AppButtonDefaults.minButtonWidth
            )
            .clip(shape)
            .clipToBounds()
            .background(containerColor)
            .run {
                if (border != null) border(border, shape)
                else this
            }
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = innerInteractionSource,
                indication = ripple(color = colors.contentColor)
            )
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
            LocalTextStyle provides AppButtonDefaults.getDefaultTextStyle()
        ) {
            content.invoke(this)
        }
    }
}

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    shape: Shape = AppButtonDefaults.iconButtonShape(),
    colors: ButtonColors = AppButtonDefaults.textButtonColors(),
    interactionSource: MutableInteractionSource? = null,
    content: @Composable (() -> Unit)
) {
    val contentColor by animateColorAsState(
        if (enabled) colors.contentColor
            else colors.disabledContentColor
    )
    val backgroundColor by animateColorAsState(
        if (enabled) colors.containerColor
        else colors.disabledContainerColor
    )
    val innerInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .defaultMinSize(
                minHeight = AppButtonDefaults.minIconButtonSize,
                minWidth = AppButtonDefaults.minIconButtonSize
            )
            .clip(shape)
            .clipToBounds()
            .background(backgroundColor)
            .clickable(
                enabled = enabled,
                onClick = onClick,
                interactionSource = innerInteractionSource,
                indication = ripple(color = colors.contentColor)
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor,
        ) {
            content.invoke()
        }
    }
}

@Immutable
object AppButtonDefaults {

    @Composable
    fun shape() = AppTheme.shape.medium

    @Composable
    fun compactShape() = AppTheme.shape.small

    @Composable
    fun iconButtonShape() = AppTheme.shape.full

    val minButtonHeight = 40.dp
    val minCompactButtonHeight = 28.dp

    val minButtonWidth = 40.dp

    val minIconButtonSize = 40.dp
    val minIconContentSize = 24.dp

    val filledButtonPadding: PaddingValues = PaddingValues(horizontal = 24.dp)
    val filledButtonWithIconPadding: PaddingValues = PaddingValues(start = 16.dp, end = 24.dp)
    val filledButtonMinIconSize = 18.dp

    val textButtonPadding: PaddingValues = PaddingValues(horizontal = 12.dp)
    val textButtonWithIconPadding: PaddingValues = PaddingValues(start = 12.dp, end = 16.dp)
    val textButtonCompactPadding: PaddingValues = PaddingValues(horizontal = 8.dp)

    @Composable
    fun getDefaultTextStyle() = AppTheme.typography.labelLarge

    @Composable
    private fun _filledButtonColors(): ButtonColors = ButtonColors(
        containerColor = AppTheme.colorScheme.primary,
        contentColor = AppTheme.colorScheme.onPrimary,
        disabledContainerColor = AppTheme.colorScheme.outline.copy(0.5f),
        disabledContentColor = AppTheme.colorScheme.outline,
    )

    @Composable
    private fun _textButtonColors(): ButtonColors = ButtonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.colorScheme.primary,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = AppTheme.colorScheme.outline,
    )

    @Composable
    fun filledButtonColors() = _filledButtonColors()

    @Composable
    fun filledButtonColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
    ): ButtonColors {
        val defaultColors = _filledButtonColors()
        return defaultColors.copy(
            containerColor = containerColor.takeOrElse { defaultColors.containerColor },
            contentColor = contentColor.takeOrElse { defaultColors.contentColor },
            disabledContainerColor = disabledContainerColor.takeOrElse { defaultColors.disabledContainerColor },
            disabledContentColor = disabledContentColor.takeOrElse { defaultColors.disabledContentColor },
        )
    }

    @Composable
    fun textButtonColors() = _textButtonColors()

    @Composable
    fun textButtonColors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
    ): ButtonColors {
        val defaultColors = _textButtonColors()
        return defaultColors.copy(
            containerColor = containerColor.takeOrElse { defaultColors.containerColor },
            contentColor = contentColor.takeOrElse { defaultColors.contentColor },
            disabledContainerColor = disabledContainerColor.takeOrElse { defaultColors.disabledContainerColor },
            disabledContentColor = disabledContentColor.takeOrElse { defaultColors.disabledContentColor },
        )
    }

}