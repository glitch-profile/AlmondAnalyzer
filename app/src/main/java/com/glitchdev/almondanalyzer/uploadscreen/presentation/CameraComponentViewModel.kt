package com.glitchdev.almondanalyzer.uploadscreen.presentation

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.core.net.toFile
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

private const val TAG = "CAMERA_COMPONENT"

class CameraComponentViewModel: ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    fun takePicture(
        outputFileOptions: ImageCapture.OutputFileOptions,
        cameraController: LifecycleCameraController,
        onImageSaved: (imageUri: Uri) -> Unit
    ) {
        cameraController.takePicture(
            outputFileOptions, executor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    if (outputFileResults.savedUri != null) {
                        processImage(outputFileResults.savedUri!!)
                        onImageSaved(outputFileResults.savedUri!!)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "save image failed with exception: ${exception.message}")
                }
            }
        )
    }

    private fun rotateImage(img: Bitmap, degree: Int): Bitmap? {
        val matrix: Matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedImg: Bitmap? =
            Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true)
        img.recycle()
        return rotatedImg
    }

    private fun processImage(uri: Uri) {
        val imageFile = uri.toFile()
        val ei: ExifInterface = ExifInterface(uri.path!!)
        val orientation =
            ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        Log.e(TAG, "orientation: $orientation", )
        var img = BitmapFactory.decodeFile(imageFile.path)
        img = when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(img, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(img, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(img, 270)
            else -> img
        }
        imageFile.delete()
        imageFile.outputStream().use { stream ->
            img!!.compress(Bitmap.CompressFormat.JPEG, 75, stream)
        }
        img?.recycle()
    }

}