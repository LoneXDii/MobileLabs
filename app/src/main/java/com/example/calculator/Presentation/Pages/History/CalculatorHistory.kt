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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.Infrastructure.Persistence.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun CalculatorHistory(
    onClose: () -> Unit
) {
    val firebase = FirebaseRepository()
    var history by remember { mutableStateOf<List<CalculatorState>>(emptyList()) }

    LaunchedEffect(Unit) {
        history = withContext(Dispatchers.IO) {
            firebase.loadOperations()
        }
    }

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
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(history.size) { index ->
                Text(
                    text = history[index].expression,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                Text(
                    text = "=" + history[index].tempResult,
                    color = Color.Green,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

        Button(
            onClick = onClose,
            modifier = Modifier.align(Alignment.Start)
                .padding(8.dp)
        ) {
            Text(text = "Закрыть")
        }
    }
}