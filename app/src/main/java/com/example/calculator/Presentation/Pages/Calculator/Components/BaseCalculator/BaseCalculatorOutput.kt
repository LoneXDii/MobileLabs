package com.example.calculator.Presentation.Pages.Calculator.Components.BaseCalculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.ui.theme.DefaultTextColor

@Composable
fun BaseCalculatorOutput(
    state: CalculatorState
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 30.dp, bottom = 10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Text(
            text = state.expression,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Light,
            fontSize = 35.sp,
            lineHeight = 40.sp,
            color = DefaultTextColor,
            maxLines = 4
        )
    }
    Text(
        text = state.tempResult,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        fontWeight = FontWeight.Light,
        fontSize = 25.sp,
        lineHeight = 30.sp,
        color = DefaultTextColor,
        maxLines = 1
    )
}