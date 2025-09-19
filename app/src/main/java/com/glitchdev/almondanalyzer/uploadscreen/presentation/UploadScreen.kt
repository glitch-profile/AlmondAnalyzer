package com.glitchdev.almondanalyzer.uploadscreen.presentation

import android.Manifest
import android.content.pm.PackageManager
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

@Composable
fun UploadScreen(
    cameraState: CameraComponentState,
    onUpdateCameraPermissions: (isPermissionGranted: Boolean) -> Unit,
    onUpdateCameraFullscreenMode: (isExpanded: Boolean) -> Unit,
    onUpdateCameraStreamStatus: (isAvailable: Boolean) -> Unit,
    onSwitchSelectedCamera: (isBackCamera: Boolean) -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) onUpdateCameraPermissions.invoke(true)
            else onUpdateCameraPermissions.invoke(false)
        }
    )

    var maxScreenHeight by remember { mutableStateOf(Dp.Unspecified) }

    LaunchedEffect(null) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ->
                onUpdateCameraPermissions.invoke(true)
            else -> onUpdateCameraPermissions.invoke(false)
        }
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
                onNoPermissionClicked = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                onExpandButtonClicked = { onUpdateCameraFullscreenMode.invoke(true) },
                onCollapseButtonClicked = { onUpdateCameraFullscreenMode.invoke(false) },
                onSwitchCameraButtonClicked = { onSwitchSelectedCamera.invoke(!cameraState.isBackCamera) },
                onUpdateCameraStreamStatus = onUpdateCameraStreamStatus,
                onPhotoTaken = { println("photo taken") },
            )
        }
    }

}