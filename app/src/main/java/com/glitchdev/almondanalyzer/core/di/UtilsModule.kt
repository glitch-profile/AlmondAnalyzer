package com.glitchdev.almondanalyzer.core.di

import com.glitchdev.almondanalyzer.ui.components.notification.SwipeableNotificationState
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val utilsModule = module {
    factoryOf(::SwipeableNotificationState)
}