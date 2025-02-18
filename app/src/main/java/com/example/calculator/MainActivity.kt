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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.calculator.ui.theme.CalculatorTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    private val sharedPreferences by lazy {
        getSharedPreferences("AppPreferences", MODE_PRIVATE)
    }

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
                val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                val vibrator = vibratorManager.defaultVibrator
                val context = LocalContext.current
                val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                val navController = rememberNavController()
                val isFirstLaunch = sharedPreferences.getBoolean("isFirstLaunch", true)

                AppNavigation(navController, isFirstLaunch, vibrator, androidId)
            }
        }
    }
}