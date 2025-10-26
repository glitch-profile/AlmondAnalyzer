package com.glitchdev.almondanalyzer.uploadscreen.presentation.imagepicker

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    state: ImagePickerState,
    onSelectImage: (imageUri: Uri) -> Unit,
    onUnselectImage: (imageUri: Uri) -> Unit,
    onClearSelection: () -> Unit
) {

}