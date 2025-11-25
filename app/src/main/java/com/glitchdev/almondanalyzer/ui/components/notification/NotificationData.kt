package com.glitchdev.almondanalyzer.ui.components.notification

data class NotificationDataState(
    val visibility: SwipeableNotificationVisibilityState = SwipeableNotificationVisibilityState.Hidden,
    val messageStr: String? = null,
    val messageRes: Int? = null,
    val titleStr: String? = null,
    val titleRes: Int? = null,
    val actionLabelStr: String? = null,
    val actionLabelRes: Int? = null,
    val isInterruptedByUser: Boolean = false,
    val isActionPerformed: Boolean = false
)

enum class SwipeableNotificationVisibilityState {
    Visible, Hidden
}

enum class SwipeableNotificationDuration(val durationMillis: Long?) {
    Short(2_000), Default(5_000), UntilDismiss(null),
}
