package com.glitchdev.almondanalyzer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties

@Composable
fun AppPopup(
    visible: Boolean,
    onDismissRequest: (() -> Unit)? = null,
    isFocusable: Boolean = true,
    dismissOnBackPress: Boolean = true,
    content: @Composable () -> Unit
) {
    var showPopupScreen by remember {
        mutableStateOf(visible)
    }
    val contentVisibilityState = remember { MutableTransitionState(visible) }

    LaunchedEffect(visible) {
        if (visible) {
            showPopupScreen = true
            contentVisibilityState.targetState = true
        } else {
            contentVisibilityState.targetState = false
        }
    }
    LaunchedEffect(contentVisibilityState.currentState) {
        if (!contentVisibilityState.currentState) showPopupScreen = false
    }

    if (showPopupScreen) {
        Popup(
            popupPositionProvider = AppPopupDefault.absoluteFullscreenPosition,
            onDismissRequest = onDismissRequest,
            properties = PopupProperties(
                focusable = isFocusable,
                clippingEnabled = false,
                dismissOnClickOutside = dismissOnBackPress
            )
        ) {
            AnimatedVisibility(
                visibleState = contentVisibilityState,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                content.invoke()
            }
        }
    }
}

@Composable
fun AppPopupDialog(
    visible: Boolean,
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    if (visible) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                decorFitsSystemWindows = true,
                usePlatformDefaultWidth = false
            )
        ) {
            (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.95f)
            content.invoke()
        }

    }
}

@Immutable
object AppPopupDefault {

    val defaultScrimColor = Color.Black.copy(0.4f)

    val absoluteFullscreenPosition = object: PopupPositionProvider {
        override fun calculatePosition(
            anchorBounds: IntRect,
            windowSize: IntSize,
            layoutDirection: LayoutDirection,
            popupContentSize: IntSize
        ): IntOffset = IntOffset.Zero
    }

}