package com.example.calculator.Presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.example.calculator.Infrastructure.Persistence.FirebaseRepository
import com.example.calculator.Presentation.Pages.Calculator.BaseCalculator
import com.example.calculator.Presentation.Pages.Calculator.ScientificCalculator
import com.example.calculator.Presentation.Pages.Camera.CameraScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("HardwareIds")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalculatorApp(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp
) {
    val context = LocalContext.current
    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    val firebase = FirebaseRepository(androidId)
    val configuration = LocalConfiguration.current

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            firebase.loadUiTheme()
        }
    }

    if (viewModel.isCameraOpen) {
        CameraScreen(viewModel)
    }
    else {
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            BaseCalculator(
                state = viewModel.state,
                onAction = viewModel::onAction,
                buttonSpacing = buttonSpacing,
                modifier = modifier,
                onCameraButton = { viewModel.isCameraOpen = true },
                onSetValue = { expr: String -> viewModel.setExpression(expr) }
            )
        } else {
            ScientificCalculator(
                state = viewModel.state,
                onAction = viewModel::onAction,
                buttonSpacing = buttonSpacing,
                modifier = modifier,
                onCameraButton = { viewModel.isCameraOpen = true },
                onSetValue = { expr: String -> viewModel.setExpression(expr) }
            )
        }
    }
}