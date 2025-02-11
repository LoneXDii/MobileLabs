package com.example.calculator.Presentation

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.Presentation.Pages.Calculator.BaseCalculator
import com.example.calculator.Presentation.Pages.Calculator.ScientificCalculator
import com.example.calculator.Presentation.Pages.Camera.CameraScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalculatorApp(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    viewModel: CalculatorViewModel
) {
    val configuration = LocalConfiguration.current

    if (viewModel.isCameraOpen) {
        CameraScreen(viewModel)
    }
    else {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            BaseCalculator(
                state = state,
                onAction = viewModel::onAction,
                buttonSpacing = buttonSpacing,
                modifier = modifier,
                onCameraButton = { viewModel.isCameraOpen = true }
            )
        } else {
            ScientificCalculator(
                state = state,
                onAction = viewModel::onAction,
                buttonSpacing = buttonSpacing,
                modifier = modifier,
                onCameraButton = { viewModel.isCameraOpen = true }
            )
        }
    }
}