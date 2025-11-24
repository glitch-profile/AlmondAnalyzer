package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Back
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun JumpToTopButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit,
    inverseDirection: Boolean = false,
    shape: Shape = JumpToTopButtonDefaults.shape(),
    colors: JumpToTopButtonDefaults.JumpToTopButtonColors = JumpToTopButtonDefaults.colors(),
) {
    val transition = updateTransition(
        if (enabled) JumpToTopButtonVisibility.Visible
            else JumpToTopButtonVisibility.Gone
    )

    val hiddenOffset = remember {
        if (inverseDirection) JumpToTopButtonDefaults.defaultHiddenOffset * -1
        else JumpToTopButtonDefaults.defaultHiddenOffset
    }
    val visibleOffset = remember {
        if (inverseDirection) JumpToTopButtonDefaults.defaultVisibleOffset * -1
        else JumpToTopButtonDefaults.defaultVisibleOffset
    }
    val topOffset = transition.animateDp(
        transitionSpec = {
            spring(stiffness = Spring.StiffnessMedium)
        }
    ) {
        if (it == JumpToTopButtonVisibility.Gone) hiddenOffset
            else visibleOffset
    }

    if (topOffset.value != hiddenOffset) {
        val density = LocalDensity.current
        Box(
            modifier = modifier
                .fillMaxWidth()
                .offset {
                    with(density) {
                        IntOffset(x = 0, y = topOffset.value.roundToPx())
                    }
                },
            contentAlignment = if (inverseDirection) Alignment.BottomCenter
                else Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .clip(shape)
                    .background(colors.containerColor)
                    .clickable(
                        onClick = onClick,
                        interactionSource = null,
                        indication = ripple(color = colors.contentColor)
                    )
                    .padding(JumpToTopButtonDefaults.paddingValues()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .rotate(if (inverseDirection) -90f else 90f),
                    imageVector = AppIcons.Back,
                    contentDescription = null,
                    tint = colors.contentColor
                )
                Spacer(Modifier.width(AppTheme.size.small))
                val buttonTextRes = remember {
                    if (inverseDirection) R.string.components_jump_to_bottom_text
                        else R.string.components_jump_to_top_text
                }
                Text(
                    text = stringResource(buttonTextRes),
                    style = AppTheme.typography.labelLarge,
                    color = colors.contentColor
                )
            }
        }
    }
}

@Immutable
object JumpToTopButtonDefaults {

    @Composable
    fun shape() = AppTheme.shape.medium

    val defaultHiddenOffset = (-48).dp
    val defaultVisibleOffset = 16.dp

    data class JumpToTopButtonColors(
        val contentColor: Color,
        val containerColor: Color
    )

    @Composable
    fun paddingValues() = PaddingValues(
        horizontal = AppTheme.size.large,
        vertical = AppTheme.size.small
    )

    @Composable
    private fun _colors() = JumpToTopButtonColors(
        contentColor = AppTheme.colorScheme.primary,
        containerColor = AppTheme.colorScheme.background
    )

    @Composable
    fun colors() = _colors()

    @Composable
    fun colors(
        contentColor: Color = Color.Unspecified,
        containerColor: Color = Color.Unspecified
    ): JumpToTopButtonColors {
        val defaultColors = _colors()
        return defaultColors.copy(
            contentColor = contentColor.takeOrElse { defaultColors.contentColor },
            containerColor = containerColor.takeOrElse { defaultColors.containerColor }
        )
    }
}

enum class JumpToTopButtonVisibility {
    Visible, Gone
}