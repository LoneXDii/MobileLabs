package com.example.calculator.Presentation.Pages.Customization

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.Colors
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorEditorComponent(
    colorName: String,
    color: Color,
    onColorChange: (Color) -> Unit
) {
    var showColorPicker by remember { mutableStateOf(false) }
    val colorPickerController = rememberColorPickerController()

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = colorName,
            color = Colors.DefaultTextColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(color)
                .border(width = 2.dp, color = Colors.DefaultTextColor)
                .clickable {
                    showColorPicker = true
                }
        )
    }

    if (showColorPicker) {
        AlertDialog(
            onDismissRequest = { showColorPicker = false },
            title = { Text("Выберите цвет") },
            text = {
                Column {
                    HsvColorPicker(
                        controller = colorPickerController,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Выбранный цвет: ${colorPickerController.selectedColor.value}")
                }
            },
            confirmButton = {
                Button(onClick = {
                    onColorChange(colorPickerController.selectedColor.value)
                    showColorPicker = false
                }) {
                    Text("Применить")
                }
            },
            dismissButton = {
                Button(onClick = { showColorPicker = false }) {
                    Text("Отмена")
                }
            }
        )
    }
}