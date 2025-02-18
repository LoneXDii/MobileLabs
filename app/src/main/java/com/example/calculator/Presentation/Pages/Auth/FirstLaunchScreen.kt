package com.example.calculator.Presentation.Pages.Auth

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calculator.ui.theme.Colors

@Composable
fun FirstLaunchScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    val sharedPreferences = LocalContext.current
        .getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    Box(modifier = Modifier
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
                label = { Text("Set Password") },
                textStyle = TextStyle(color = Colors.DefaultTextColor),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Colors.OperationButtonColor,
                    contentColor = Colors.DefaultTextColor
                ),
                onClick = {
                    if (password.isNotEmpty()) {
                        sharedPreferences.edit().apply {
                            putString("password", password)
                            putBoolean("isFirstLaunch", false)
                            apply()
                        }
                        navController.navigate("main/true") {
                            popUpTo("firstLaunch") { inclusive = true }
                        }
                    }
                }
            ) {
                Text("Submit")
            }
        }
    }
}