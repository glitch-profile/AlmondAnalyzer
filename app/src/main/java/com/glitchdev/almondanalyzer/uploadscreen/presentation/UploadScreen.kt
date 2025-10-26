package com.glitchdev.almondanalyzer.uploadscreen.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.glitchdev.almondanalyzer.ui.theme.appSpringDefault
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraComponent
import com.glitchdev.almondanalyzer.uploadscreen.presentation.cameracomponent.CameraComponentState
import com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker.ImagePickerState

@Composable
fun UploadScreen(
    cameraState: CameraComponentState,
    imagePickerState: ImagePickerState,
    onUpdateCameraPermissions: (isPermissionGranted: Boolean) -> Unit,
    onUpdateCameraFullscreenMode: (isExpanded: Boolean) -> Unit,
    onUpdateCameraStreamStatus: (isAvailable: Boolean) -> Unit,
    onSwitchSelectedCamera: (isBackCamera: Boolean) -> Unit,
    onPhotoTaken: (imageUri: Uri) -> Unit,
    onUpdateImagePickerPermissions: (isPermissionsGranted: Boolean) -> Unit,
    onSelectImage: (imageUri: Uri) -> Unit,
    onUnselectImage: (imageUri: Uri) -> Unit,
    onClearImageSelection: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val cameraPermission = Manifest.permission.CAMERA
    val imagePickerPermission = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) Manifest.permission.READ_MEDIA_IMAGES
        else Manifest.permission.READ_EXTERNAL_STORAGE
    }

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { onUpdateCameraPermissions.invoke(it) }
    )

    val imagePickerPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { onUpdateImagePickerPermissions.invoke(it) }
    )

    var maxScreenHeight by remember { mutableStateOf(Dp.Unspecified) }

    LaunchedEffect(null) {
        val isHasCameraAccess = ContextCompat.checkSelfPermission(context, cameraPermission) ==
                PackageManager.PERMISSION_GRANTED
        onUpdateCameraPermissions.invoke(isHasCameraAccess)
        val isHasImagesAccess = ContextCompat.checkSelfPermission(context, imagePickerPermission) ==
                PackageManager.PERMISSION_GRANTED
        onUpdateImagePickerPermissions.invoke(isHasImagesAccess)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { newSize ->
                maxScreenHeight = with(density) { newSize.height.toDp() }
            }
    ) {
        val cameraWindowHeight by animateDpAsState(
            targetValue = if (cameraState.isExpanded) maxScreenHeight else 200.dp,
            animationSpec = appSpringDefault()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = cameraWindowHeight)
        ) {
            CameraComponent(
                modifier = Modifier
                    .fillMaxSize(),
                state = cameraState,
                onNoPermissionClicked = { cameraPermissionLauncher.launch(cameraPermission) },
                onExpandButtonClicked = { onUpdateCameraFullscreenMode.invoke(true) },
                onCollapseButtonClicked = { onUpdateCameraFullscreenMode.invoke(false) },
                onSwitchCameraButtonClicked = { onSwitchSelectedCamera.invoke(!cameraState.isBackCamera) },
                onUpdateCameraStreamStatus = onUpdateCameraStreamStatus,
                onPhotoTaken = onPhotoTaken,
            )
        }
    }

}