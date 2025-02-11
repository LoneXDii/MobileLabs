package com.example.calculator.Presentation

import CameraPreview
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
import androidx.core.content.ContextCompat
import com.example.calculator.Application.Services.TextRecognizer
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import java.util.concurrent.Executors

@Composable
fun CameraScreen(viewModel: CalculatorViewModel) {
    val context = LocalContext.current
    val textRecognizer = remember { TextRecognizer(context) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val preview = remember { Preview.Builder().build() }
    val imageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }

    val cameraProvider = cameraProviderFuture.get()
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    LaunchedEffect(Unit) {
        cameraProviderFuture.addListener({
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageCapture
            )
        }, ContextCompat.getMainExecutor(context))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(preview, Modifier.fillMaxSize())
        Button(
            onClick = {
                textRecognizer.takePhotoAndRecognizeText(imageCapture, cameraExecutor, viewModel)
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Capture and Recognize")
        }
    }
}