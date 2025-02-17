package com.example.calculator

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibratorManager
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.example.calculator.Application.ViewModel.CalculatorViewModelFactory
import com.example.calculator.Presentation.CalculatorApp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.Colors
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @SuppressLint("HardwareIds")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 1)
        }

        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val buttonSpacing = 8.dp
                val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator
                val context = LocalContext.current
                val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

                val viewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModelFactory(vibrator, androidId))

                CalculatorApp(
                    viewModel = viewModel,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Colors.MainBackground)
                        .padding(vertical = 16.dp, horizontal = 8.dp)
                )
            }
        }
    }
}