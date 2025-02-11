package com.example.calculator.Presentation.Pages.Camera

import CameraPreview
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.calculator.Infrastructure.Services.TextRecognizer
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.example.calculator.Presentation.Pages.Camera.Components.RequestCameraPermission

@Composable
fun CameraScreen(viewModel: CalculatorViewModel) {
    val context = LocalContext.current
    val textRecognizer = remember { TextRecognizer(context) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val preview = remember { Preview.Builder().build() }
    val imageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }

    RequestCameraPermission()

    LaunchedEffect(Unit) {
        val cameraProvider = cameraProviderFuture.get()
        try {
            cameraProvider.unbindAll()

            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        } catch (e: Exception) {
            Log.e("CameraScreen", "Failed to bind camera use cases", e)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(preview, Modifier.fillMaxSize())
        Button(
            onClick = {
                textRecognizer.takePhotoAndRecognizeText(imageCapture, viewModel)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Capture and Recognize")
        }
        Button(
            onClick = {
                viewModel.isCameraOpen = false
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ){
            Text("Close")
        }
    }
}
