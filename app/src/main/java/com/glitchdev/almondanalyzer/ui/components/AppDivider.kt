package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun AppTextDivider(
    modifier: Modifier = Modifier,
    text: String
) {
    CompositionLocalProvider(
        LocalContentColor provides AppTheme.colorScheme.outline
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = LocalContentColor.current
            )
            Spacer(Modifier.width(AppTheme.size.extraSmall))
            Text(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = AppTheme.typography.bodyMedium,
                color = LocalContentColor.current
            )
            Spacer(Modifier.width(AppTheme.size.extraSmall))
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = LocalContentColor.current
            )
        }
    }
}