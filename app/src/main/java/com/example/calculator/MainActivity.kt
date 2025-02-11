package com.example.calculator

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.example.calculator.Application.ViewModel.CalculatorViewModelFactory
import com.example.calculator.Presentation.CalculatorApp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.MediumGray

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                val buttonSpacing = 8.dp
                val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator

                val viewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModelFactory(vibrator))

                CalculatorApp(
                    viewModel = viewModel,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MediumGray)
                        .padding(16.dp),
                )
            }
        }
    }
}