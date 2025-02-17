package com.example.calculator.Presentation.Pages.Customization

import android.annotation.SuppressLint
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.calculator.Infrastructure.Persistence.FirebaseRepository
import com.example.calculator.ui.theme.Colors
import com.example.calculator.ui.theme.DefaultDefaultTextColor
import com.example.calculator.ui.theme.DefaultHelpActionButtonColor
import com.example.calculator.ui.theme.DefaultMainBackground
import com.example.calculator.ui.theme.DefaultNumericButtonColor
import com.example.calculator.ui.theme.DefaultOperationButtonColor
import com.example.calculator.ui.theme.DefaultScientificOperationsButtonColor
import com.example.calculator.ui.theme.DefaultSecondaryTextColor

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("HardwareIds")
@Composable
fun ColorsSettings() {
    val context = LocalContext.current
    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    val firebase = FirebaseRepository(androidId)

    val colorItems = listOf(
        ColorItem("Text", Colors.DefaultTextColor) { Colors.DefaultTextColor = it },
        ColorItem("Text 2", Colors.SecondaryTextColor) { Colors.SecondaryTextColor = it },
        ColorItem("Scientific", Colors.ScientificOperationsButtonColor) { Colors.ScientificOperationsButtonColor = it },
        ColorItem("Operation", Colors.OperationButtonColor) { Colors.OperationButtonColor = it },
        ColorItem("Action", Colors.HelpActionButtonColor) { Colors.HelpActionButtonColor = it },
        ColorItem("Numeric", Colors.NumericButtonColor) { Colors.NumericButtonColor = it },
        ColorItem("Back", Colors.MainBackground) { Colors.MainBackground = it }
    )

    Column() {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxWidth()
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    firebase.saveUiTheme()
                },
                modifier = Modifier
                    .width(130.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Colors.OperationButtonColor,
                    contentColor = Colors.DefaultTextColor
                )
            ) {
                Text(text = "Save")
            }

            Button(
                onClick = {
                    Colors.DefaultTextColor = DefaultDefaultTextColor
                    Colors.NumericButtonColor = DefaultNumericButtonColor
                    Colors.OperationButtonColor = DefaultOperationButtonColor
                    Colors.SecondaryTextColor = DefaultSecondaryTextColor
                    Colors.MainBackground = DefaultMainBackground
                    Colors.HelpActionButtonColor = DefaultHelpActionButtonColor
                    Colors.ScientificOperationsButtonColor = DefaultScientificOperationsButtonColor
                },
                modifier = Modifier
                    .width(130.dp)
                    .padding(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor  = Colors.OperationButtonColor,
                    contentColor = Colors.DefaultTextColor
                )
            ) {
                Text(text = "Refresh")
            }
        }
    }
}