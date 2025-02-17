package com.example.calculator.Presentation.Pages.History

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.Infrastructure.Persistence.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("HardwareIds")
@Composable
fun CalculatorHistory()
{
    val context = LocalContext.current
    val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    val firebase = FirebaseRepository(androidId)
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
            text = "History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(history.size) { index ->
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        Text(
                            text = history[index].expression,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp,
                            lineHeight = 25.sp,
                            color = Color.White,
                            maxLines = 2
                        )
                        Text(
                            text = "=" + history[index].tempResult,
                            color = Color.Green,
                            fontSize = 25.sp,
                            lineHeight = 30.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }

        Button(
            onClick = { firebase.clearHistory() },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp)
        ) {
            Text(text = "Clear")
        }
    }
}