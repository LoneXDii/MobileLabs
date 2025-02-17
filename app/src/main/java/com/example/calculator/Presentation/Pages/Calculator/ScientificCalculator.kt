package com.example.calculator.Presentation.Pages.Calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.Presentation.Pages.Calculator.Components.ScientificCalculator.ScientificCalculatorButtons
import com.example.calculator.Presentation.Pages.History.CalculatorHistory

@Composable
fun ScientificCalculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    onCameraButton: () -> Unit
) {
    var isHistoryOpen = remember { mutableStateOf(false) }
    var isSwiping = false

    Box(
        modifier = modifier
            .pointerInput(Unit){
                detectHorizontalDragGestures(
                    onHorizontalDrag = { change, dragAmount ->
                        if (dragAmount < -75 && !isSwiping) {
                            isSwiping = true
                            onAction(CalculatorAction.Delete)
                            change.consume()
                        }
                    },
                    onDragEnd = {
                        isSwiping = false
                    }
                )
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = state.expression,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 25.sp,
                    lineHeight = 30.sp,
                    color = Color.White,
                    maxLines = 1
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 0.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { isHistoryOpen.value = !isHistoryOpen.value }
                    ) {
                        Icon(
                            imageVector = if (isHistoryOpen.value) Icons.Default.Calculate else Icons.Default.History,
                            contentDescription = "History",
                            tint = Color.White,
                            modifier = Modifier.padding(2.dp)
                                .size(25.dp)
                        )
                    }

                    IconButton(
                        onClick = onCameraButton
                    ) {
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = "Open Camera",
                            tint = Color.White,
                            modifier = Modifier.padding(2.dp)
                                .size(25.dp)
                        )
                    }

                    IconButton(
                        onClick = { /* Действие для настроек */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(25.dp)
                        )
                    }

                    Text(
                        text = state.tempResult,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        fontWeight = FontWeight.Light,
                        fontSize = 20.sp,
                        lineHeight = 25.sp,
                        color = Color.White,
                        maxLines = 1
                    )
                }
            }

            if(!isHistoryOpen.value) {
                ScientificCalculatorButtons(onAction, buttonSpacing)
            }
            else {
                CalculatorHistory()
            }
        }
    }
}