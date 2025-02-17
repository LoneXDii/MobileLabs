package com.example.calculator.Presentation.Pages.Customization

import androidx.compose.ui.graphics.Color

data class ColorItem(
    val name: String,
    var color: Color,
    val onColorChange: (Color) -> Unit
)