package com.glitchdev.almondanalyzer.uploadscreen.presentation

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraComponentState
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraStatusState
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraStreamStatus
import com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker.ImagePickerState
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList


class UploadScreenViewModel: ViewModel() {

    private val _cameraState = MutableStateFlow(CameraComponentState())
    val cameraState = _cameraState.asStateFlow()

    private val _pickerState = MutableStateFlow(ImagePickerState())
    val pickerState = _pickerState.asStateFlow()

    private val _takenPhotos = MutableStateFlow<List<Uri>>(emptyList())
    val takenPhotos = _takenPhotos.asStateFlow()

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

    fun onPhotoTaken(imageUri: Uri) {
        _takenPhotos.update { takenPhotos.value.toMutableList().apply {
            add(imageUri)
            toImmutableList()
        } }
        _pickerState.update {
            it.copy(
                images = pickerState.value.images.toMutableList().apply {
                    add(0, imageUri)
                    toImmutableList()
                }
            )
        }
        addImageToSelection(imageUri)
    }

    fun clearTakenPhotos() {
        Log.i("TAG", "deleting ${takenPhotos.value}", )
        takenPhotos.value.forEach {
            val imageFile = it.toFile()
            if (imageFile.exists()) {
                imageFile.delete()
            }
        }
    }

    fun loadImagesUris(context: Context) {
        println("updating uris")
        viewModelScope.launch {
            val uris = mutableListOf<Uri>()
            uris.addAll(takenPhotos.value.reversed())
            val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(MediaStore.Images.Media._ID)
            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

            context.contentResolver.query(
                collection,
                projection,
                null,
                null,
                sortOrder
            )?.use { cursor ->
                val columnId = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(columnId)
                    val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                    uris.add(uri)
                }
            }
            _pickerState.update { it.copy(images = uris.toImmutableList()) }
        }
    }
    fun onUpdateImagePickerPermissions(isPermissionsGranted: Boolean) {
        if (isPermissionsGranted) {
            _pickerState.update { it.copy(hasPermissions = true) }
        }
    }
    fun hideTempOnlyFilesWarning() {
        _pickerState.update { it.copy(isShowTempOnlyWarning = false) }
    }
    fun addImageToSelection(uri: Uri) {
        if (!pickerState.value.selectedImages.contains(uri)) {
            _pickerState.update {
                it.copy(
                    selectedImages = pickerState.value.selectedImages.toMutableList().apply {
                        add(uri)
                        toImmutableList()
                    }
                )
            }
        }
    }

    fun removeImageFromSelection(uri: Uri) {
        if (pickerState.value.selectedImages.contains(uri)) {
            _pickerState.update {
                it.copy(
                    selectedImages = pickerState.value.selectedImages.toMutableList().apply {
                        remove(uri)
                        toImmutableList()
                    }
                )
            }
        }
    }

    fun clearSelection() {
        _pickerState.update { it.copy(selectedImages = emptyList()) }
    }

    override fun onCleared() {
        Log.i("TAG", "view model clearing...", )
        clearTakenPhotos()
        super.onCleared()
    }

}