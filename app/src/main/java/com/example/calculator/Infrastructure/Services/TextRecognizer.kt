package com.example.calculator.Infrastructure.Services

import android.content.Context
import android.util.Log
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File

class TextRecognizer(private val context: Context) {

    fun takePhotoAndRecognizeText(
        imageCapture: ImageCapture,
        viewModel: CalculatorViewModel
    ) {
        val executor = ContextCompat.getMainExecutor(context)

        val file = File.createTempFile("IMG_", ".jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

        imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                recognizeTextFromImage(file, viewModel)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraScreen", "Failed to capture image", exception)
                viewModel.isCameraOpen = false
            }
        })
    }

    private fun recognizeTextFromImage(
        file: File,
        viewModel: CalculatorViewModel)
    {
        val image = InputImage.fromFilePath(context, file.toUri())
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val recognizedText = visionText.text
                viewModel.setExpression(recognizedText)
                viewModel.isCameraOpen = false
            }
            .addOnFailureListener { _ ->
                viewModel.isCameraOpen = false
            }
    }
}