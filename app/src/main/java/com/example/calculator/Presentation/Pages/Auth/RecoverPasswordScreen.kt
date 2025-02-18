package com.example.calculator.Presentation.Pages.Auth

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calculator.Infrastructure.Services.BiometricPromptManager
import com.example.calculator.ui.theme.Colors

@Composable
fun RecoverPasswordScreen(navController: NavController, biometricPromptManager: BiometricPromptManager) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val sharedPreferences = LocalContext.current
        .getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    var biometricResult by remember { mutableStateOf<BiometricPromptManager.BiometricResult?>(null) }

    LaunchedEffect(Unit) {
        biometricPromptManager.promptResults.collect { result ->
            biometricResult = result
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(Colors.MainBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Colors.MainBackground),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Recover Password",
                style = MaterialTheme.typography.bodyLarge,
                color = Colors.DefaultTextColor
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                textStyle = TextStyle(color = Colors.DefaultTextColor),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = errorMessage != null
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                textStyle = TextStyle(color = Colors.DefaultTextColor),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = errorMessage != null
            )

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.OperationButtonColor,
                    contentColor = Colors.DefaultTextColor
                ),
                onClick = {
                    biometricPromptManager.showBiometricPrompt(
                        title = "Authenticate Your Identity",
                        description = "Please use your fingerprint or face scan to securely recover your password."
                    )
                },
                modifier = Modifier.width(130.dp)
            ) {
                Text("Authenticate")
            }

            biometricResult?.let { result ->
                errorMessage = when(result){
                    is BiometricPromptManager.BiometricResult.AuthenticationError -> {
                        "Authentication error: ${result.error}"
                    }
                    BiometricPromptManager.BiometricResult.AuthenticationFailed -> {
                        "Authentication failed"
                    }
                    BiometricPromptManager.BiometricResult.AuthenticationNotSet -> {
                        "Authentication not set"
                    }
                    BiometricPromptManager.BiometricResult.FeatureUnavailable -> {
                        "Feature unavailable"
                    }
                    BiometricPromptManager.BiometricResult.HardwareUnavailable -> {
                        "Hardware unavailable"
                    }
                    BiometricPromptManager.BiometricResult.AuthenticationSuccess -> {
                        if (newPassword.isNotEmpty() && newPassword == confirmPassword) {
                            sharedPreferences.edit().putString("password", newPassword).apply()
                            navController.navigate("login") {
                                popUpTo("recoverPassword") { inclusive = true }
                            }

                            biometricResult = null
                            null
                        }
                        else {
                            "Passwords do not match or are empty."
                        }
                    }
                }
            }
        }
    }
}