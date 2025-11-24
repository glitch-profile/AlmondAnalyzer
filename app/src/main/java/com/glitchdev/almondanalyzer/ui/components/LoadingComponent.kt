package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.glitchdev.almondanalyzer.R
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun LoadingComponent(
    modifier: Modifier = Modifier
) {
   Column(
       modifier = modifier
           .fillMaxSize(),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       CircularProgressIndicator(
           modifier = Modifier
               .size(36.dp),
           strokeWidth = 3.dp,
           color = AppTheme.colorScheme.primary
       )
       Spacer(modifier.height(AppTheme.size.small))
       Text(
           text = stringResource(R.string.components_loading_text),
           color = AppTheme.colorScheme.primary,
           style = AppTheme.typography.bodyLarge
       )
   }
}