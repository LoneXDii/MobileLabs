package com.example.calculator.Application.Services

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.net.toUri
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.File
import java.util.concurrent.Executor

class TextRecognizer(private val context: Context) {

    fun takePhotoAndRecognizeText(
        imageCapture: ImageCapture,
        executor: Executor,
        viewModel: CalculatorViewModel
    ) {
        val file = File.createTempFile("IMG_", ".jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

        imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                recognizeTextFromImage(file, viewModel)
            }

            override fun onError(exception: ImageCaptureException) {
                // Handle error
            }
        })
    }

    private fun recognizeTextFromImage(file: File, viewModel: CalculatorViewModel) {
        val image = InputImage.fromFilePath(context, file.toUri())
        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                val recognizedText = visionText.text
                viewModel.setExpression(recognizedText)
            }
            .addOnFailureListener { e ->
                // Handle error
            }
    }
}