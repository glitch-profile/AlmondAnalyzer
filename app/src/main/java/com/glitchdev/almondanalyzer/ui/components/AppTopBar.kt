package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import kotlin.math.roundToInt

@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    appBarHeight: Dp = AppTopBarDefaults.defaultAppBarHeight,
    colors: AppTopBarDefaults.AppTopBarColors = AppTopBarDefaults.colors(),
    contentPadding: PaddingValues = AppTopBarDefaults.appTopBarPaddingValues()
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(appBarHeight)
            .background(colors.containerColor)
            .padding(contentPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(
            LocalContentColor provides colors.contentColor,
            LocalTextStyle provides AppTopBarDefaults.defaultTextStyle()
        ) {
            if (navigationIcon != null) {
                Row {
                    Box(
                        modifier = Modifier
                            .defaultMinSize(
                                minHeight = AppTopBarDefaults.defaultIconHeight,
                                minWidth = AppTopBarDefaults.defaultIconHeight
                            ),
                        propagateMinConstraints = true
                    ) {
                        navigationIcon.invoke()
                    }
                    Spacer(Modifier.width(AppTheme.size.small))
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    title.invoke()
                }
                if (actions != null) {
                    val density = LocalDensity.current
                    val iconSpacing = AppTheme.size.small

                    Spacer(Modifier.width(AppTheme.size.small))
                    Layout(
                        modifier = Modifier,
                        content = actions
                    ) { measurements, constraints ->
                        val minHeight = with(density) { AppTopBarDefaults.defaultIconHeight.toPx().roundToInt() }
                        val spacing = with(density) { iconSpacing.toPx().roundToInt() }

                        val placeables = measurements.map { measurable ->
                            measurable.measure(Constraints.fixed(width = minHeight, height = minHeight))
                        }
                        val requestedHeight = placeables.maxOf { it.height }
                        val requestedWidth = placeables.sumOf { it.width } + (spacing * placeables.size - 2)
                        layout(width = requestedWidth, height = minHeight) {
                            var xPosition = 0
                            placeables.forEach { placeable ->
                                placeable.placeRelative(
                                    y = (requestedHeight / 2) - (placeable.height / 2),
                                    x = xPosition
                                )
                                xPosition += placeable.width + spacing
                            }
                        }
                    }
                }
            }
        }
    }
}

@Immutable
object AppTopBarDefaults {

    val defaultAppBarHeight = 56.dp
    val defaultIconHeight = 28.dp

    @Composable
    fun appTopBarPaddingValues() = PaddingValues(
        horizontal = AppTheme.size.medium
    )

    @Composable
    fun defaultTextStyle() = AppTheme.typography.titleMedium

    data class AppTopBarColors(
        val contentColor: Color,
        val containerColor: Color
    )

    @Composable
    private fun _colors() = AppTopBarColors(
        contentColor = AppTheme.colorScheme.onSurface,
        containerColor = AppTheme.colorScheme.surface
    )

    @Composable
    fun colors() = _colors()

    @Composable
    fun color(
        contentColor: Color = Color.Unspecified,
        containerColor: Color = Color.Unspecified
    ): AppTopBarColors {
        val defaultColors = _colors()
        return defaultColors.copy(
            contentColor = contentColor.takeOrElse { defaultColors.contentColor },
            containerColor = containerColor.takeOrElse { defaultColors.containerColor }
        )
    }

}