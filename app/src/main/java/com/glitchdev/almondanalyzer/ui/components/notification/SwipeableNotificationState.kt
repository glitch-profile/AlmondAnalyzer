package com.glitchdev.almondanalyzer.ui.components.notification

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SwipeableNotificationState {

    private var _notificationDataState = MutableStateFlow(NotificationDataState())
    val notificationDataState = _notificationDataState.asStateFlow()

    private var scope = CoroutineScope(Dispatchers.Default + Job())

    fun showNotification(data: NotificationAction) {
        scope.coroutineContext.cancelChildren()
        val showNotificationJob = scope.launch {
            _notificationDataState.update {
                NotificationDataState(
                    visibility = SwipeableNotificationVisibilityState.Visible,
                    titleStr = data.titleStr,
                    titleRes = data.titleRes,
                    messageStr = data.messageStr,
                    messageRes = data.messageRes,
                    actionLabelStr = data.actionLabelStr,
                    actionLabelRes = data.actionLabelRes
                )
            }
            if (data.duration.durationMillis != null) {
                delay(data.duration.durationMillis)
                closeNotification()
            }
        }
        showNotificationJob.invokeOnCompletion {
            if (notificationDataState.value.isInterruptedByUser) {
                if (notificationDataState.value.isActionPerformed) data.onActionPerformed?.invoke()
                else data.onDismiss?.invoke()
            } else data.onTimeout?.invoke()
        }
    }

    private fun closeNotification() {
        _notificationDataState.update {
            it.copy(visibility = SwipeableNotificationVisibilityState.Hidden)
        }
    }

    fun dismissNotification() {
        _notificationDataState.update {
            it.copy(
                visibility = SwipeableNotificationVisibilityState.Hidden,
                isInterruptedByUser = true
            )
        }
        scope.coroutineContext.cancelChildren()
    }

    fun performNotificationAction() {
        _notificationDataState.update {
            it.copy(
                visibility = SwipeableNotificationVisibilityState.Hidden,
                isInterruptedByUser = true,
                isActionPerformed = true
            )
        }
        scope.coroutineContext.cancelChildren()
    }

}