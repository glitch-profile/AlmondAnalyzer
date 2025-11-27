package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault

@Composable
fun AppOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = AppTextFieldDefaults.shape(),
    colors: AppTextFieldDefaults.AppTextFieldColors = AppTextFieldDefaults.colors(),
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    contentPadding: PaddingValues = PaddingValues(AppTheme.size.large),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isTextFieldFocused by remember {
        mutableStateOf(false)
    }
    val transition = updateTransition(enabled)
    val containerColor by transition.animateColor(transitionSpec = { appSpringDefault() }) { isEnabled ->
        if (isEnabled) {
            if (isTextFieldFocused) colors.focusedContainerColor
            else colors.unfocusedContainerColor
        } else colors.disabledContainerColor
    }
    val textColor by transition.animateColor(transitionSpec = { appSpringDefault() }) { isEnabled ->
        if (isEnabled) {
            if (isTextFieldFocused) colors.focusedTextColor
            else colors.unfocusedTextColor
        } else colors.disabledTextColor
    }
    val indicatorColor by transition.animateColor(transitionSpec = { appSpringDefault() }) { isEnabled -> 
        if (isEnabled) {
            if (isTextFieldFocused) colors.focusedIndicatorColor
            else colors.unfocusedIndicatorColor
        } else colors.disabledIndicatorColor
    }
//    val containerColor by animateColorAsState(
//        targetValue = if (enabled) {
//            if (isTextFieldFocused) colors.focusedContainerColor
//            else colors.unfocusedContainerColor
//        } else colors.disabledContainerColor
//    )
//    val textColor by animateColorAsState(
//        if (enabled) {
//            if (isTextFieldFocused) colors.focusedTextColor
//            else colors.unfocusedTextColor
//        } else colors.disabledTextColor
//    )
//    val indicatorColor by animateColorAsState(
//        if (enabled) {
//            if (isTextFieldFocused) colors.focusedIndicatorColor
//            else colors.unfocusedIndicatorColor
//        } else colors.disabledIndicatorColor
//    )
    val indicatorWidth by animateDpAsState(
        if (isTextFieldFocused) AppTextFieldDefaults.focusedIndicatorWidth
        else AppTextFieldDefaults.unfocusedIndicatorWidth
    )

    BasicTextField(
        modifier = modifier
            .defaultMinSize(minHeight = AppTextFieldDefaults.minTextHeight)
            .onFocusChanged {
                isTextFieldFocused = it.hasFocus
            },
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        textStyle = AppTextFieldDefaults.textStyle().copy(color = textColor),
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .clip(shape)
                    .background(containerColor)
                    .border(
                        width = indicatorWidth,
                        color = indicatorColor,
                        shape = shape
                    )
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    val leadingIconColor by animateColorAsState(
                        targetValue = if (enabled) {
                            if (isTextFieldFocused) colors.focusedLeadingIconColor
                            else colors.unfocusedLeadingIconColor
                        } else colors.disabledLeadingIconColor
                    )
                    CompositionLocalProvider(LocalContentColor provides leadingIconColor) {
                        leadingIcon.invoke()
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (label != null && value.isEmpty()) {
                        val labelColor by animateColorAsState(
                            if (enabled) {
                                if (isTextFieldFocused) colors.focusedLabelColor
                                else colors.unfocusedLabelColor
                            } else colors.disabledLabelColor
                        )
                        CompositionLocalProvider(
                            LocalContentColor provides labelColor,
                            LocalTextStyle provides AppTextFieldDefaults.textStyle()
                        ) {
                            label.invoke()
                        }
                    }
                    CompositionLocalProvider(LocalContentColor provides textColor) {
                        innerTextField.invoke()
                    }
                }
                if (trailingIcon != null) {
                    val trailingIconColor by animateColorAsState(
                        targetValue = if (enabled) {
                            if (isTextFieldFocused) colors.focusedTrailingIconColor
                            else colors.unfocusedTrailingIconColor
                        } else colors.disabledTrailingIconColor
                    )
                    CompositionLocalProvider(LocalContentColor provides trailingIconColor) {
                        trailingIcon.invoke()
                    }
                }
            }
        }
    )

}

@Composable
fun AppFilledTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    shape: Shape = AppTextFieldDefaults.shape(),
    colors: AppTextFieldDefaults.AppTextFieldColors = AppTextFieldDefaults.colors(),
    label: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    contentPadding: PaddingValues = PaddingValues(AppTheme.size.large),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    var isTextFieldFocused by remember {
        mutableStateOf(false)
    }
    val containerColor by animateColorAsState(
        targetValue = if (enabled) {
            if (isTextFieldFocused) colors.focusedContainerColor
            else colors.unfocusedContainerColor
        } else colors.disabledContainerColor
    )
    val textColor by animateColorAsState(
        if (enabled) {
            if (isTextFieldFocused) colors.focusedTextColor
            else colors.unfocusedTextColor
        } else colors.disabledTextColor
    )

    BasicTextField(
        modifier = modifier
            .onFocusChanged {
                isTextFieldFocused = it.hasFocus
            },
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        textStyle = AppTextFieldDefaults.textStyle().copy(color = textColor),
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .clip(shape)
                    .background(containerColor)
                    .padding(contentPadding),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.size.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) {
                    val leadingIconColor by animateColorAsState(
                        targetValue = if (enabled) {
                            if (isTextFieldFocused) colors.focusedLeadingIconColor
                            else colors.unfocusedLeadingIconColor
                        } else colors.disabledLeadingIconColor
                    )
                    CompositionLocalProvider(LocalContentColor provides leadingIconColor) {
                        leadingIcon.invoke()
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    contentAlignment = Alignment.TopStart
                ) {
                    if (label != null && value.isEmpty()) {
                        val labelColor by animateColorAsState(
                            if (enabled) {
                                if (isTextFieldFocused) colors.focusedLabelColor
                                else colors.unfocusedLabelColor
                            } else colors.disabledLabelColor
                        )
                        CompositionLocalProvider(
                            LocalContentColor provides labelColor,
                            LocalTextStyle provides AppTextFieldDefaults.textStyle()
                        ) { label.invoke() }
                    }
                    CompositionLocalProvider(LocalContentColor provides textColor) {
                        innerTextField.invoke()
                    }
                }
                if (trailingIcon != null) {
                    val trailingIconColor by animateColorAsState(
                        targetValue = if (enabled) {
                            if (isTextFieldFocused) colors.focusedTrailingIconColor
                            else colors.unfocusedTrailingIconColor
                        } else colors.disabledTrailingIconColor
                    )
                    CompositionLocalProvider(LocalContentColor provides trailingIconColor) {
                        trailingIcon.invoke()
                    }
                }
            }
        }
    )
}


@Immutable
object AppTextFieldDefaults {

    @Composable
    fun textStyle() = AppTheme.typography.bodyLarge

    @Composable
    fun shape() = AppTheme.shape.large

    val minTextHeight = 56.dp

    val unfocusedIndicatorWidth: Dp = 1.dp
    val focusedIndicatorWidth: Dp = 2.dp

    data class AppTextFieldColors(
        val focusedContainerColor: Color,
        val focusedTextColor: Color,
        val focusedIndicatorColor: Color,
        val focusedLeadingIconColor: Color,
        val focusedTrailingIconColor: Color,
        val focusedLabelColor: Color,

        val unfocusedContainerColor: Color,
        val unfocusedTextColor: Color,
        val unfocusedIndicatorColor: Color,
        val unfocusedLeadingIconColor: Color,
        val unfocusedTrailingIconColor: Color,
        val unfocusedLabelColor: Color,

        val disabledContainerColor: Color,
        val disabledTextColor: Color,
        val disabledIndicatorColor: Color,
        val disabledLeadingIconColor: Color,
        val disabledTrailingIconColor: Color,
        val disabledLabelColor: Color,
    )

    @Composable
    private fun _colors() = AppTextFieldColors(
        focusedContainerColor = AppTheme.colorScheme.surface,
        focusedTextColor = AppTheme.colorScheme.onSurface,
        focusedIndicatorColor = AppTheme.colorScheme.primary,
        focusedLeadingIconColor = AppTheme.colorScheme.primary,
        focusedTrailingIconColor = AppTheme.colorScheme.onSurface,
        focusedLabelColor = AppTheme.colorScheme.onSurfaceVariant,
        unfocusedContainerColor = AppTheme.colorScheme.surface,
        unfocusedTextColor = AppTheme.colorScheme.onSurface.copy(0.7f),
        unfocusedIndicatorColor = AppTheme.colorScheme.outline,
        unfocusedLeadingIconColor = AppTheme.colorScheme.outline,
        unfocusedTrailingIconColor = AppTheme.colorScheme.onSurfaceVariant.copy(0.7f),
        unfocusedLabelColor = AppTheme.colorScheme.onSurfaceVariant.copy(0.7f),
        disabledContainerColor = AppTheme.colorScheme.outline.copy(0.3f),
        disabledTextColor = AppTheme.colorScheme.outline,
        disabledIndicatorColor = AppTheme.colorScheme.outline,
        disabledLeadingIconColor = AppTheme.colorScheme.outline,
        disabledTrailingIconColor = AppTheme.colorScheme.outline,
        disabledLabelColor = AppTheme.colorScheme.outline,
    )

    @Composable
    fun colors() = _colors()

    @Composable
    fun colors(
        focusedContainerColor: Color = Color.Unspecified,
        focusedTextColor: Color = Color.Unspecified,
        focusedIndicatorColor: Color = Color.Unspecified,
        focusedLeadingIconColor: Color = Color.Unspecified,
        focusedTrailingIconColor: Color = Color.Unspecified,
        focusedLabelColor: Color = Color.Unspecified,
        unfocusedContainerColor: Color = Color.Unspecified,
        unfocusedTextColor: Color = Color.Unspecified,
        unfocusedIndicatorColor: Color = Color.Unspecified,
        unfocusedLeadingIconColor: Color = Color.Unspecified,
        unfocusedTrailingIconColor: Color = Color.Unspecified,
        unfocusedLabelColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledTextColor: Color = Color.Unspecified,
        disabledIndicatorColor: Color = Color.Unspecified,
        disabledLeadingIconColor: Color = Color.Unspecified,
        disabledTrailingIconColor: Color = Color.Unspecified,
        disabledLabelColor: Color = Color.Unspecified,
    ): AppTextFieldColors {
        val defaultColors = _colors()
        return defaultColors.copy(
            focusedContainerColor = focusedContainerColor.takeOrElse { defaultColors.focusedContainerColor },
            focusedTextColor = focusedTextColor.takeOrElse { defaultColors.focusedTextColor },
            focusedIndicatorColor = focusedIndicatorColor.takeOrElse { defaultColors.focusedIndicatorColor },
            focusedLeadingIconColor = focusedLeadingIconColor.takeOrElse { defaultColors.focusedLeadingIconColor },
            focusedTrailingIconColor = focusedTrailingIconColor.takeOrElse { defaultColors.focusedTrailingIconColor },
            focusedLabelColor = focusedLabelColor.takeOrElse { defaultColors.focusedLabelColor },
            unfocusedContainerColor = unfocusedContainerColor.takeOrElse { defaultColors.unfocusedContainerColor },
            unfocusedTextColor = unfocusedTextColor.takeOrElse { defaultColors.unfocusedTextColor },
            unfocusedIndicatorColor = unfocusedIndicatorColor.takeOrElse { defaultColors.unfocusedIndicatorColor },
            unfocusedLeadingIconColor = unfocusedLeadingIconColor.takeOrElse { defaultColors.unfocusedLeadingIconColor },
            unfocusedTrailingIconColor = unfocusedTrailingIconColor.takeOrElse { defaultColors.unfocusedTrailingIconColor },
            unfocusedLabelColor = unfocusedLabelColor.takeOrElse { defaultColors.unfocusedLabelColor },
            disabledContainerColor = disabledContainerColor.takeOrElse { defaultColors.disabledContainerColor },
            disabledTextColor = disabledTextColor.takeOrElse { defaultColors.disabledTextColor },
            disabledIndicatorColor = disabledIndicatorColor.takeOrElse { defaultColors.disabledIndicatorColor },
            disabledLeadingIconColor = disabledLeadingIconColor.takeOrElse { defaultColors.disabledLeadingIconColor },
            disabledTrailingIconColor = disabledTrailingIconColor.takeOrElse { defaultColors.disabledTrailingIconColor },
            disabledLabelColor = disabledLabelColor.takeOrElse { defaultColors.disabledLabelColor }
        )
    }

}