package com.glitchdev.almondanalyzer.uploadscreen.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.glitchdev.almondanalyzer.ui.theme.AppTheme

@Composable
fun UploadScreen(
    cameraState: CameraComponentState,
    onUpdateCameraState: (newState: CameraComponentState) -> Unit
) {
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) onUpdateCameraState.invoke(CameraComponentState.COMPACT)
            else onUpdateCameraState.invoke(CameraComponentState.NO_PERMISSIONS)
        }
    )

    LaunchedEffect(null) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) ->
                onUpdateCameraState.invoke(CameraComponentState.COMPACT)
            else -> onUpdateCameraState.invoke(CameraComponentState.NO_PERMISSIONS)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val cameraWindowHeight by animateDpAsState(
            targetValue = if (cameraState == CameraComponentState.EXPANDED) 600.dp else 200.dp
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
                onExpandClicked = { onUpdateCameraState.invoke(CameraComponentState.EXPANDED) },
                onCollapseClicked = { onUpdateCameraState.invoke(CameraComponentState.COMPACT) },
                onPhotoTaken = {  }
            )
        }
        Spacer(Modifier.height(AppTheme.size.medium))
        Button(
            onClick = {
                onUpdateCameraState.invoke(
                    if (cameraState == CameraComponentState.COMPACT) CameraComponentState.EXPANDED
                    else CameraComponentState.COMPACT
                )
            }
        ) {
            Text(text = "Change size")
        }
    }

}