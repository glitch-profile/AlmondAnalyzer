package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun AppExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    isExpanded: Boolean,
    onClick: (() -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE,
    contentPadding: PaddingValues = AppExpandableTextDefaults.defaultContentPadding,
    colors: AppExpandableTextDefaults.AppExpandableTextColors = AppExpandableTextDefaults.colors(),
    textStyle: TextStyle = AppExpandableTextDefaults.textStyle()
) {
    val density = LocalDensity.current

    var lineCount by remember { mutableIntStateOf(0) }
    var isExpandable by remember { mutableStateOf(false) }

    val textMeasurer = rememberTextMeasurer()
    val singleLineHeight = remember(textStyle, textMeasurer) {
        val height = textMeasurer.measure(text = " ", style = textStyle).size.height
        with(density) {height.toDp()}
    }

    var textWidth by remember { mutableStateOf(Dp.Unspecified) }
    var showMoreIndicationWidth by remember { mutableStateOf(Dp.Unspecified) }
    var showMoreIndicationOffset by remember { mutableStateOf(Dp.Unspecified) }

    val collapsableTextHeight by animateDpAsState(
        if (isExpanded) (singleLineHeight * lineCount)
        else (singleLineHeight * maxLines)
    )

    Box(
        modifier = modifier
            .clickable(
                enabled = isExpandable && onClick != null,
                interactionSource = null,
                indication = ripple(color = colors.actionButtonColor, radius = textWidth),
                onClick = { onClick?.invoke() }
            )
            .padding(contentPadding)
    ) {
        Box(
            modifier = Modifier
                .height(
                    if (!isExpandable) singleLineHeight * lineCount
                        else collapsableTextHeight
                )
                .clipToBounds()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .onSizeChanged {
                        with(density) { textWidth = it.width.toDp() }
                    },
                text = text,
                style = textStyle,
                color = colors.contentColor,
                onTextLayout = { textLayoutResult ->
                    if (lineCount == 0) {
                        lineCount = textLayoutResult.lineCount
                        if (textLayoutResult.lineCount > maxLines) {
                            isExpandable = true
                            if (showMoreIndicationOffset == Dp.Unspecified) {
                                showMoreIndicationOffset = with(density) {
                                    textLayoutResult.multiParagraph.getLineWidth(maxLines - 1).toDp()
                                }
                            }
                        }
                    }
                },
                softWrap = true
            )
            if (isExpandable) {
                Row(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    val isShowMoreActionIntersectsWithText =
                        (textWidth - showMoreIndicationOffset) < (showMoreIndicationWidth + 8.dp)

                    Box(
                        modifier = if (!isShowMoreActionIntersectsWithText)
                            Modifier.width(showMoreIndicationOffset)
                        else Modifier.weight(1f)
                    )
                    AnimatedVisibility(visible = !isExpanded) {
                        Row(
                            modifier = Modifier.onSizeChanged {
                                showMoreIndicationWidth = with(density) {
                                    it.width.toDp()
                                }
                            }
                        ) {
                            if (isShowMoreActionIntersectsWithText) {
                                Box(
                                    modifier = Modifier
                                        .width(32.dp)
                                        .height(singleLineHeight)
                                        .background(
                                            brush = Brush.linearGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    colors.containerColor
                                                )
                                            )
                                        )
                                )
                            }
                            Box(
                                Modifier
                                    .height(singleLineHeight)
                                    .width(AppTheme.size.extraSmall)
                                    .background(colors.containerColor)
                            )
                            Text(
                                modifier = Modifier.background(AppTheme.colorScheme.surface),
                                text = stringResource(R.string.components_expandable_text_show_more),
                                style = textStyle,
                                color = colors.actionButtonColor
                            )
                        }
                    }
                }
            }
        }

    }
}

@Immutable
object AppExpandableTextDefaults {

    @Composable
    fun textStyle() = AppTheme.typography.bodyMedium

    val defaultContentPadding: PaddingValues = PaddingValues(0.dp)

    data class AppExpandableTextColors(
        val contentColor: Color,
        val actionButtonColor: Color,
        val containerColor: Color,
    )

    @Composable
    private fun _colors() = AppExpandableTextColors(
        contentColor = AppTheme.colorScheme.onSurface,
        actionButtonColor = AppTheme.colorScheme.primary,
        containerColor = AppTheme.colorScheme.surface
    )

    @Composable
    fun colors() = _colors()

    @Composable
    fun colors(
        contentColor: Color = Color.Unspecified,
        actionButtonColor: Color = Color.Unspecified,
        containerColor: Color = Color.Unspecified
    ): AppExpandableTextColors {
        val defaultColors = _colors()
        return defaultColors.copy(
            contentColor = contentColor.takeOrElse { defaultColors.contentColor },
            actionButtonColor = actionButtonColor.takeOrElse { defaultColors.actionButtonColor },
            containerColor = containerColor.takeOrElse { defaultColors.containerColor }
        )
    }

}