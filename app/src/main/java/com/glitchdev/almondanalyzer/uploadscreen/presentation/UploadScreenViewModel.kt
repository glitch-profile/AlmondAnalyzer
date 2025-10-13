package com.glitchdev.almondanalyzer.uploadscreen.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UploadScreenViewModel: ViewModel() {

    private val _cameraState = MutableStateFlow(CameraComponentState())
    val cameraState = _cameraState.asStateFlow()

    fun onUpdateCameraPermissions(isPermissionsGranted: Boolean) {
        _cameraState.update {
            if (isPermissionsGranted) {
                it.copy(
                    cameraStatus = CameraStatusState.READY
                )
            } else {
                it.copy(
                    cameraStatus = CameraStatusState.NO_PERMISSIONS,
                    isExpanded = false
                )
            }
        }
    }

    fun onUpdateCameraFullscreenMode(isCameraExpanded: Boolean) {
        if (cameraState.value.cameraStatus == CameraStatusState.READY) {
            _cameraState.update { it.copy(isExpanded = isCameraExpanded) }
        }
    }

    fun onUpdateSelectedCamera(isBackCameraSelected: Boolean) {
        if (cameraState.value.cameraStatus == CameraStatusState.READY) {
            _cameraState.update { it.copy(isBackCamera = isBackCameraSelected) }
        }
    }

    fun onUpdateCameraStreamAvailability(isAvailable: Boolean) {
        _cameraState.update { it.copy(isCameraStreamAvailable = isAvailable) }
    }

}