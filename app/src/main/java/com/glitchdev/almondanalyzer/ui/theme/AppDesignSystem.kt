package com.glitchdev.almondanalyzer.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class AppColorsScheme(
    val background: Color,
    val primary: Color,
    val primaryVariant: Color,
    val onPrimary: Color,
    val onPrimaryVariant: Color,
    val label: Color,
    val surface: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val notation: Color,
    val onNotation: Color
) {

    @Composable
    private fun animateColor(targetColor: Color) = animateColorAsState(
        targetValue = targetColor,
        animationSpec = tween(durationMillis = 300)
    )

    fun switch() = copy(
        background = background,
        primary = primary,
        primaryVariant = primaryVariant,
        onPrimary = onPrimary,
        onPrimaryVariant = onPrimaryVariant,
        label = label,
        surface = surface,
        onSurface = onSurface,
        onSurfaceVariant = onSurfaceVariant,
        outline = outline,
        notation = notation,
        onNotation = onNotation
    )

}

data class AppTypography(
    val titleSmall: TextStyle,
    val titleMedium: TextStyle,
    val titleLarge: TextStyle,
    val bodySmall: TextStyle,
    val bodyMedium: TextStyle,
    val bodyLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelLarge: TextStyle,
)

data class AppShape(
    val none: Shape,
    val extraSmall: Shape,
    val small: Shape,
    val medium: Shape,
    val large: Shape,
    val full: Shape
)

data class AppSize(
    val extraSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val extraLarge: Dp
)

val LocalAppColorsScheme = staticCompositionLocalOf {
    AppColorsScheme(
        background = Color.Unspecified,
        primary = Color.Unspecified,
        primaryVariant = Color.Unspecified,
        onPrimary = Color.Unspecified,
        onPrimaryVariant = Color.Unspecified,
        label = Color.Unspecified,
        surface = Color.Unspecified,
        onSurface = Color.Unspecified,
        onSurfaceVariant = Color.Unspecified,
        outline = Color.Unspecified,
        notation = Color.Unspecified,
        onNotation = Color.Unspecified
    )
}

val LocalAppTypography = staticCompositionLocalOf {
    AppTypography(
        titleSmall = TextStyle.Default,
        titleMedium = TextStyle.Default,
        titleLarge = TextStyle.Default,
        bodySmall = TextStyle.Default,
        bodyMedium = TextStyle.Default,
        bodyLarge = TextStyle.Default,
        labelMedium = TextStyle.Default,
        labelLarge = TextStyle.Default
    )
}

val LocalAppShape = staticCompositionLocalOf {
    AppShape(
        none = RectangleShape,
        extraSmall = RectangleShape,
        small = RectangleShape,
        medium = RectangleShape,
        large = RectangleShape,
        full = RectangleShape
    )
}

val LocalAppSize = staticCompositionLocalOf {
    AppSize(
        extraSmall = Dp.Unspecified,
        small = Dp.Unspecified,
        medium = Dp.Unspecified,
        large = Dp.Unspecified,
        extraLarge = Dp.Unspecified
    )
}