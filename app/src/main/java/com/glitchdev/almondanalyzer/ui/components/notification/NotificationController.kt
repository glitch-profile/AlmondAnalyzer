package com.glitchdev.almondanalyzer.ui.components.notification

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

data class NotificationAction(
    val messageStr: String? = null,
    val messageRes: Int? = null,
    val titleStr: String? = null,
    val titleRes: Int? = null,
    val actionLabelStr: String? = null,
    val actionLabelRes: Int? = null,
    val duration: SwipeableNotificationDuration = SwipeableNotificationDuration.Default,
    val onTimeout: (() -> Unit)? = null,
    val onDismiss: (() -> Unit)? = null,
    val onActionPerformed: (() -> Unit)? = null
)

object NotificationController {

    private val _events = Channel<NotificationAction>()
    val notificationEvents = _events.receiveAsFlow()

    fun sendEvent(action: NotificationAction) {
        _events.trySend(action)
    }

}