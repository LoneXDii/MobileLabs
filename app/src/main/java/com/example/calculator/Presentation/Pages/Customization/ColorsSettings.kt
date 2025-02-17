package com.example.calculator.Presentation.Pages.Customization

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.Colors

@Composable
fun ColorsSettings() {
    val colorItems = listOf(
        ColorItem("Text", Colors.DefaultTextColor) { Colors.DefaultTextColor = it },
        ColorItem("Text 2", Colors.SecondaryTextColor) { Colors.SecondaryTextColor = it },
        ColorItem("Scientific", Colors.ScientificOperationsButtonColor) { Colors.ScientificOperationsButtonColor = it },
        ColorItem("Operation", Colors.OperationButtonColor) { Colors.OperationButtonColor = it },
        ColorItem("Action", Colors.HelpActionButtonColor) { Colors.HelpActionButtonColor = it },
        ColorItem("Numeric", Colors.NumericButtonColor) { Colors.NumericButtonColor = it },
        ColorItem("Background", Colors.MainBackground) { Colors.MainBackground = it }
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(colorItems.size) { index ->
            val colorItem = colorItems[index]
            ColorEditorComponent(
                colorName = colorItem.name,
                color = colorItem.color,
                onColorChange = colorItem.onColorChange
            )
        }
    }
}