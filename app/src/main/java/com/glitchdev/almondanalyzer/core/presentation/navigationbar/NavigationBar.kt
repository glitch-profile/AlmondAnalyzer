package com.glitchdev.almondanalyzer.core.presentation.navigationbar

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.core.utils.ScreenRoutes
import com.glitchdev.almondanalyzer.ui.icons.AppIcons
import com.glitchdev.almondanalyzer.ui.icons.svgs.Ai
import com.glitchdev.almondanalyzer.ui.icons.svgs.Aifilled
import com.glitchdev.almondanalyzer.ui.icons.svgs.Recents
import com.glitchdev.almondanalyzer.ui.icons.svgs.RecentsFilled
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun NavigationBar(
    currentNavGraph: NavGraph?,
    onNavigateToRecentsScreen: () -> Unit,
    onNavigateToAnalyzeScreen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.recents_screen_title),
            defaultIcon = AppIcons.Recents,
            selectedIcon = AppIcons.RecentsFilled,
            isSelected = currentNavGraph
                ?.hasRoute(ScreenRoutes.RecentsNavGraph::class) == true,
            onClick = onNavigateToRecentsScreen
        )
        NavigationItem(
            modifier = Modifier.weight(1f),
            title = stringResource(R.string.analyze_screen_title),
            defaultIcon = AppIcons.Ai,
            selectedIcon = AppIcons.Aifilled,
            isSelected = currentNavGraph
                ?.hasRoute(ScreenRoutes.AnalyzeImageNavGraph::class) == true,
            onClick = onNavigateToAnalyzeScreen
        )
    }
}

@Composable
private fun NavigationItem(
    modifier: Modifier = Modifier,
    title: String,
    defaultIcon: ImageVector,
    selectedIcon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) AppTheme.colorScheme.primary
            else AppTheme.colorScheme.onSurface
    )
    Column(
        modifier = modifier
            .padding(AppTheme.size.extraSmall)
            .clip(AppTheme.shape.medium)
            .clickable(
                enabled = true,
                onClick = onClick,
                interactionSource = null,
                indication = ripple(color = AppTheme.colorScheme.primary)
            )
            .padding(vertical = AppTheme.size.small),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CompositionLocalProvider(
            LocalContentColor provides contentColor
        ) {
            Crossfade(
                targetState = isSelected,
                animationSpec = spring(stiffness = Spring.StiffnessMedium)
            ) { showSelectedIcon ->
                if (showSelectedIcon) {
                    Icon(
                        imageVector = selectedIcon,
                        contentDescription = null,
                        tint = LocalContentColor.current,
                    )
                } else {
                    Icon(
                        imageVector = defaultIcon,
                        contentDescription = null,
                        tint = LocalContentColor.current,
                    )
                }
            }
            Spacer(Modifier.height(AppTheme.size.extraSmall))
            Text(
                text = title,
                color = LocalContentColor.current,
                style = AppTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}