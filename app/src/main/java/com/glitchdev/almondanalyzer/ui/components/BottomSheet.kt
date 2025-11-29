package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    colors: AppBottomSheetDefaults.AppBottomSheetColors = AppBottomSheetDefaults.colors(),
    shape: Shape = AppBottomSheetDefaults.shape(),
    shouldDismissOnBackPressed: Boolean = true,
    containerPadding: PaddingValues = AppBottomSheetDefaults.containerPadding(),
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        sheetState = sheetState,
        shape = RectangleShape,
        containerColor = Color.Transparent,
        contentColor = colors.contentColor,
        tonalElevation = 0.dp,
        scrimColor = colors.scrimColor,
        dragHandle = {
            Box(
                modifier = Modifier
                    .padding(bottom = AppTheme.size.medium)
                    .width(56.dp)
                    .height(AppTheme.size.extraSmall)
                    .background(
                        color = colors.handleColor,
                        shape = AppTheme.shape.full
                    )

            )
        },
        contentWindowInsets = { WindowInsets.systemBars },
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = shouldDismissOnBackPressed
        ),
        content = {
            Column(
                modifier
                    .fillMaxWidth()
                    .padding(containerPadding)
                    .shadow(
                        elevation = AppTheme.size.medium,
                        shape = shape,
                    )
                    .background(
                        color = colors.containerColor,
                        shape = shape
                    )
                    .clip(shape)
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides colors.contentColor
                ) {
                    content.invoke(this)
                }
            }
        }
    )
}

@Immutable
object AppBottomSheetDefaults {

    data class AppBottomSheetColors(
        val containerColor: Color,
        val handleColor: Color,
        val contentColor: Color,
        val scrimColor: Color
    )

    val maxSheetWidth: Dp = Dp.Unspecified

    val defaultBottomSheetTitleButtonSize = 48.dp

    @Composable
    fun containerPadding(): PaddingValues = PaddingValues(
        start = AppTheme.size.medium,
        end = AppTheme.size.medium,
        bottom = AppTheme.size.medium
    )

    @Composable
    fun contentPadding(): PaddingValues = PaddingValues(AppTheme.size.medium)

    @Composable
    fun shape() = AppTheme.shape.large

    @Composable
    private fun _colors() = AppBottomSheetColors(
        containerColor = AppTheme.colorScheme.background,
        handleColor = AppTheme.colorScheme.surface.copy(0.5f),
        contentColor = AppTheme.colorScheme.onSurface,
        scrimColor = Color.Black.copy(0.4f)
    )

    @Composable
    fun colors() = _colors()

    @Composable
    fun colors(
        containerColor: Color = Color.Unspecified,
        handleColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        scrimColor: Color = Color.Unspecified
    ): AppBottomSheetColors {
        val defaultColors = _colors()
        return defaultColors.copy(
            containerColor = containerColor.takeOrElse { defaultColors.containerColor },
            handleColor = handleColor.takeOrElse { defaultColors.handleColor },
            contentColor = contentColor.takeOrElse { defaultColors.contentColor },
            scrimColor = scrimColor.takeOrElse { defaultColors.scrimColor }
        )
    }

}