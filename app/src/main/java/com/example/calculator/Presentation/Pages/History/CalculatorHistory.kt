package com.example.calculator.Presentation.Pages.History

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorHistory(
    onClose: () -> Unit
) {
    val history = listOf(
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "3 + 5 = 8",
        "12 / 4 = 3",
        "7 * 6 = 42",
        "9 - 2 = 7"
    )

    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxSize()
    ) {
        Text(
            text = "История",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(history.size) { index ->
                Text(
                    text = history[index],
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        Button(
            onClick = onClose,
            modifier = Modifier.align(Alignment.Start)
                .padding(16.dp)
        ) {
            Text(text = "Закрыть")
        }
    }
}