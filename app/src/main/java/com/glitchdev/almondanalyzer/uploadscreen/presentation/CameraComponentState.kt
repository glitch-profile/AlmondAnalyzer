package com.glitchdev.almondanalyzer.uploadscreen.presentation

data class CameraComponentState(
    val cameraStatus: CameraStatusState = CameraStatusState.INITIALIZING,
    val isCameraStreamAvailable: Boolean = false,
    val isBackCamera: Boolean = true,
    val isExpanded: Boolean = false
)

enum class CameraStatusState {
    INITIALIZING, NO_PERMISSIONS, READY
}
