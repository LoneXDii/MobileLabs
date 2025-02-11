package com.example.calculator.Presentation.Pages.Calculator.Components

import androidx.compose.ui.graphics.Color

data class ButtonConfig(
    val symbol: String,
    val backgroundColor: Color,
    val aspectRatio: Float,
    val onClick: () -> Unit
)