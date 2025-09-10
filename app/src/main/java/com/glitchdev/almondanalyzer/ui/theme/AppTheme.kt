package com.glitchdev.almondanalyzer.ui.theme

import android.app.Activity
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

private val lightColorScheme = AppColorsScheme(
    background = backgroundLight,
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryVariant = primaryVariantLight,
    onPrimaryVariant = onPrimaryVariantLight,
    label = labelBlue,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    notation = notationLight,
    onNotation = onNotationLLight
)

private val darkColorsScheme = AppColorsScheme(
    background = backgroundDark,
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryVariant = primaryVariantDark,
    onPrimaryVariant = onPrimaryVariantDark,
    label = labelBlue,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    notation = notationDark,
    onNotation = onNotationDark
)

private val typography = AppTypography(
    titleSmall = TextStyle(
        fontFamily = inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontFamily = inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    titleLarge = TextStyle(
        fontFamily = inter,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = inter,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyLarge = TextStyle(
        fontFamily = inter,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    labelMedium = TextStyle(
        fontFamily = inter,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
    ),
    labelLarge = TextStyle(
        fontFamily = inter,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    ),
)

private val shape = AppShape(
    none = RectangleShape,
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    full = CircleShape
)

private val size = AppSize(
    extraSmall = 4.dp,
    small = 8.dp,
    medium = 12.dp,
    large = 16.dp,
    extraLarge = 24.dp
)

@Composable
fun AlmondAnalyzerTheme(
    useSystemTheme: Boolean,
    useDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val isDarkTheme = if (useSystemTheme) isSystemInDarkTheme()
        else useDarkTheme
    val colorScheme = if (isDarkTheme) darkColorsScheme
        else lightColorScheme
    val rippleIndicator = ripple(color = Color.DarkGray)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !isDarkTheme
            insetsController.isAppearanceLightNavigationBars = !isDarkTheme
        }
    }
    CompositionLocalProvider(
        LocalAppColorsScheme provides colorScheme.switch(),
        LocalAppTypography provides typography,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalIndication provides rippleIndicator,
        content = content
    )
}

object AppTheme {

    val colorScheme: AppColorsScheme
        @Composable get() = LocalAppColorsScheme.current

    val typography: AppTypography
        @Composable get() = LocalAppTypography.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current

}