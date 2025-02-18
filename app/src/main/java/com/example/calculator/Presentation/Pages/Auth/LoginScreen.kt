package com.example.calculator.Presentation.Pages.Auth

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calculator.Infrastructure.Services.BiometricPromptManager
import com.example.calculator.ui.theme.Colors

@Composable
fun LoginScreen(navController: NavController, biometricPromptManager: BiometricPromptManager) {
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val sharedPreferences = LocalContext.current.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    val savedPassword = sharedPreferences.getString("password", "")

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
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
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

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.OperationButtonColor,
                        contentColor = Colors.DefaultTextColor
                    ),
                    onClick = {
                        if (password == savedPassword) {
                            errorMessage = null
                            navController.navigate("main/true") {
                                popUpTo("login") { inclusive = true }
                            }
                        } else {
                            errorMessage = "Wrong password. Please try again."
                        }
                    },
                    modifier = Modifier.width(130.dp)
                ) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.OperationButtonColor,
                        contentColor = Colors.DefaultTextColor
                    ),
                    onClick = {
                        biometricPromptManager.showBiometricPrompt(
                            title = "Authenticate Your Identity",
                            description = "Please use your fingerprint or face scan to securely access your account."
                        )
                    },
                    modifier = Modifier.width(130.dp)
                ) {
                    Text("Biometric")
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.NumericButtonColor,
                        contentColor = Colors.DefaultTextColor
                    ),
                    onClick = {
                        errorMessage = null
                        navController.navigate("main/false") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    modifier = Modifier.width(130.dp)
                ) {
                    Text(
                        text = "Unauthorized",
                        fontSize = 10.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Colors.NumericButtonColor,
                        contentColor = Colors.DefaultTextColor
                    ),
                    onClick = {
                        errorMessage = null
                        navController.navigate("forgotPassword") {
                            popUpTo("login") { inclusive = false }
                        }
                    },
                    modifier = Modifier.width(130.dp)
                ) {
                    Text(
                        text = "Forgot password?",
                        fontSize = 10.sp
                    )
                }
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
                        navController.navigate("main/true") {
                            popUpTo("login") { inclusive = true }
                        }

                        biometricResult = null
                        null
                    }
                }
            }
        }
    }
}