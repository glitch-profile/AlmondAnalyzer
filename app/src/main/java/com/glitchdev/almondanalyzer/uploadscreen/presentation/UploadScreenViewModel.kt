package com.glitchdev.almondanalyzer.uploadscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.Executors


class UploadScreenViewModel: ViewModel() {

    private val _cameraState = MutableStateFlow(CameraComponentState())
    val cameraState = _cameraState.asStateFlow()

    private val fileSaveExecutor = Executors.newSingleThreadExecutor()

    private var updateCameraStreamStatusJob: Job? = null

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

    fun onUpdateCameraStreamAvailability(isStreamAvailable: Boolean) {
        if (isStreamAvailable) {
            _cameraState.update { it.copy(cameraStreamStatus = CameraStreamStatus.OK) }
        } else {
            _cameraState.update { it.copy(cameraStreamStatus = CameraStreamStatus.LOADING) }
            updateCameraStreamStatusJob?.cancel()
            updateCameraStreamStatusJob = viewModelScope.launch {
                delay(2000) // Seconds to wait for stream to start
                if (cameraState.value.cameraStreamStatus != CameraStreamStatus.OK)
                    _cameraState.update { it.copy(cameraStreamStatus = CameraStreamStatus.ERROR) }
            }
        }
    }

}