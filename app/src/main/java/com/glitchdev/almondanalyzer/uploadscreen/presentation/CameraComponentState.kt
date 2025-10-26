package com.glitchdev.almondanalyzer.uploadscreen.presentation

data class CameraComponentState(
    val cameraStatus: CameraStatusState = CameraStatusState.INITIALIZING,
    val cameraStreamStatus: CameraStreamStatus = CameraStreamStatus.NO_STREAM,
    val isBackCamera: Boolean = true,
    val isExpanded: Boolean = false
)

enum class CameraStatusState {
    INITIALIZING, NO_PERMISSIONS, READY
}

enum class CameraStreamStatus {
    NO_STREAM, LOADING, ERROR, OK
}
