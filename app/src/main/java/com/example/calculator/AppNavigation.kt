package com.example.calculator

import android.content.Context
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.calculator.Application.ViewModel.CalculatorViewModel
import com.example.calculator.Application.ViewModel.CalculatorViewModelFactory
import com.example.calculator.Infrastructure.Services.BiometricPromptManager
import com.example.calculator.Presentation.CalculatorApp
import com.example.calculator.Presentation.Pages.Auth.ChangePasswordScreen
import com.example.calculator.Presentation.Pages.Auth.FirstLaunchScreen
import com.example.calculator.Presentation.Pages.Auth.LoginScreen
import com.example.calculator.ui.theme.Colors

@Composable
fun AppNavigation(
    navController: NavHostController,
    isFirstLaunch: Boolean,
    vibrator: Vibrator,
    androidId: String,
    biometricPromptManager: BiometricPromptManager
) {
//    val sharedPreferences = LocalContext.current
//        .getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
//    sharedPreferences.edit().apply {
//        putBoolean("isFirstLaunch", true)
//        apply()
//    }

    NavHost(navController = navController, startDestination = if (isFirstLaunch) "firstLaunch" else "login") {
        composable("firstLaunch") {
            FirstLaunchScreen(navController)
        }

        composable("login") {
            LoginScreen(navController, biometricPromptManager)
        }

        composable("changePassword") {
            ChangePasswordScreen(navController)
        }

        composable("main/{isAuthorized}") { backStackEntry ->
            val parameter = backStackEntry.arguments?.getString("isAuthorized")
            val isAuthorized = parameter == "true"

            val viewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModelFactory(vibrator, androidId))
            val buttonSpacing = 8.dp

            CalculatorApp(
                viewModel = viewModel,
                buttonSpacing = buttonSpacing,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Colors.MainBackground)
                    .padding(vertical = 16.dp, horizontal = 8.dp),
                isAuthorized =  isAuthorized,
                navController = navController
            )
        }
    }
}