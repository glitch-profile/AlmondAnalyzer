package com.glitchdev.almondanalyzer.uploadscreen.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UploadScreenViewModel: ViewModel() {

    private val _cameraState = MutableStateFlow<CameraComponentState>(CameraComponentState.INITIALIZING)
    val cameraState = _cameraState.asStateFlow()

    fun updateState(state: CameraComponentState) {
        _cameraState.update { state }
    }

}