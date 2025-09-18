package com.glitchdev.almondanalyzer.uploadscreen.presentation

data class CameraComponentState(
    val cameraStatus: CameraStatusState = CameraStatusState.INITIALIZING,
    val isBackCamera: Boolean = true,
    val isExpanded: Boolean = false
)

enum class CameraStatusState {
    INITIALIZING, NO_PERMISSIONS, LOADING, READY
}
