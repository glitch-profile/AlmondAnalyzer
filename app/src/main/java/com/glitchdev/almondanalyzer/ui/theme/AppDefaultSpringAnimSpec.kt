package com.glitchdev.almondanalyzer.ui.theme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.spring

fun <T>appSpringDefault(): SpringSpec<T> = spring(stiffness = Spring.StiffnessMediumLow)