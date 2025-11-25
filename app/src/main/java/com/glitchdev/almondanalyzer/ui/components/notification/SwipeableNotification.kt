package com.glitchdev.almondanalyzer.ui.components.notification

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.zIndex
import com.glitchdev.almondanalyzer.ui.components.AppButtonDefaults
import com.glitchdev.almondanalyzer.ui.components.AppTextButton
import com.glitchdev.almondanalyzer.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun SwipeableNotification(
    notificationState: SwipeableNotificationState,
    shape: Shape = AppTheme.shape.medium,
    containerColor: Color = AppTheme.colorScheme.onSurface,
    contentColor: Color = AppTheme.colorScheme.surface
) {
    val notificationData = notificationState.notificationDataState.collectAsState()
        .value

    val message = if (notificationData.messageRes != null) stringResource(notificationData.messageRes)
    else notificationData.messageStr ?: ""
    val title = if (notificationData.titleRes != null) stringResource(notificationData.titleRes)
    else notificationData.titleStr
    val actionLabel = if (notificationData.actionLabelRes != null) stringResource(notificationData.actionLabelRes)
    else notificationData.actionLabelStr

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(10f),
        contentAlignment = Alignment.BottomStart
    ) {
        val scope = rememberCoroutineScope()
        val maxHeight = 200f
        val translationY = remember {
            Animatable(maxHeight, 0f)
        }
        val decay = rememberSplineBasedDecay<Float>()
        translationY.updateBounds(0f, maxHeight)
        val draggableState = rememberDraggableState(
            onDelta = { dragAmount ->
                scope.launch {
                    translationY.snapTo(translationY.value + dragAmount)
                }
            }
        )

        LaunchedEffect(notificationData.visibility) {
            if (notificationData.visibility == SwipeableNotificationVisibilityState.Visible) {
                if (translationY.value != 0f) translationY.animateTo(
                    0f,
                    animationSpec = spring(
                        stiffness = 500f
                    )
                )
            } else {
                if (translationY.value != maxHeight) translationY.animateTo(
                    maxHeight,
                    animationSpec = spring(
                        stiffness = 500f
                    )
                )
            }
        }

        if (translationY.value != maxHeight) {
            Box(
                modifier = Modifier
                    .navigationBarsPadding()
                    .imePadding()
                    .padding(
                        horizontal = AppTheme.size.medium,
                        vertical = AppTheme.size.large
                    )
                    .graphicsLayer {
                        this.translationY = translationY.value
                        this.alpha = (maxHeight - translationY.value) / maxHeight
                    }
                    .draggable(
                        draggableState,
                        enabled = notificationData.visibility == SwipeableNotificationVisibilityState.Visible,
                        orientation = Orientation.Vertical,
                        onDragStopped = { velocity ->
                            if (notificationData.visibility == SwipeableNotificationVisibilityState.Hidden) return@draggable
                            val decayY = decay.calculateTargetValue(
                                translationY.value,
                                velocity
                            )
                            scope.launch {
                                val targetY = if (decayY > maxHeight * 0.5) maxHeight
                                else 0f

                                val canReachTargetWithDecay =
                                    (decayY > targetY && targetY == maxHeight)
                                            || (decayY < targetY && targetY == 0f)

                                if (canReachTargetWithDecay) {
                                    translationY.animateDecay(
                                        initialVelocity = velocity,
                                        animationSpec = decay
                                    )
                                } else {
                                    translationY.animateTo(
                                        targetValue = targetY,
                                        initialVelocity = velocity,
                                        animationSpec = spring(
                                            stiffness = 500f
                                        )
                                    )
                                }
                                if (targetY == maxHeight) notificationState.dismissNotification()
                            }
                        }
                    )
            ) {
                Row(
                    modifier = Modifier
                        .background(containerColor, shape)
                        .padding(AppTheme.size.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides contentColor
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            if (title != null) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = title,
                                    style = AppTheme.typography.labelLarge,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Spacer(Modifier.height(AppTheme.size.extraSmall))
                            }
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = message,
                                style = AppTheme.typography.bodyMedium,
                                maxLines = 5,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    if (actionLabel != null) {
                        Spacer(Modifier.width(AppTheme.size.small))
                        AppTextButton(
                            modifier = Modifier.height(AppButtonDefaults.minCompactButtonHeight),
                            onClick = notificationState::performNotificationAction,
                            shape = AppButtonDefaults.shape(),
                            contentPadding = AppButtonDefaults.textButtonCompactPadding
                        ) {
                            Text(actionLabel)
                        }
                    }
                }
            }
        }
    }
}