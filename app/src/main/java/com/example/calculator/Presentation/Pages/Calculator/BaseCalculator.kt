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
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.Presentation.Pages.Calculator.Components.ButtonConfig
import com.example.calculator.Presentation.Pages.Calculator.Components.CalculatorButton
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange

@Composable
fun BaseCalculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    onCameraButton: () -> Unit
) {
    var isSwiping = false;
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
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(2.dp),
            horizontalAlignment = Alignment.End
        ){
            IconButton(
                onClick = onCameraButton
            ) {
                Icon(
                    imageVector = Icons.Default.Camera,
                    contentDescription = "Open Camera",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(2.dp)
                        .size(36.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = state.expression,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 35.sp,
                    lineHeight = 40.sp,
                    color = Color.White,
                    maxLines = 4
                )

                Text(
                    text = state.tempResult,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 25.sp,
                    lineHeight = 30.sp,
                    color = Color.White,
                    maxLines = 1
                )
            }

            val buttonRows = listOf(
                listOf(
                    ButtonConfig("(", Orange, 1f) { onAction(CalculatorAction.Bracket(true)) },
                    ButtonConfig(")", Orange, 1f) { onAction(CalculatorAction.Bracket(false)) },
                    ButtonConfig("Del", LightGray, 1f) { onAction(CalculatorAction.Delete) },
                    ButtonConfig("/", Orange, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) }
                ),
                listOf(
                    ButtonConfig("7", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(7)) },
                    ButtonConfig("8", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(8)) },
                    ButtonConfig("9", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(9)) },
                    ButtonConfig("*", Orange, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) }
                ),
                listOf(
                    ButtonConfig("4", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(4)) },
                    ButtonConfig("5", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(5)) },
                    ButtonConfig("6", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(6)) },
                    ButtonConfig("-", Orange, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) }
                ),
                listOf(
                    ButtonConfig("1", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(1)) },
                    ButtonConfig("2", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(2)) },
                    ButtonConfig("3", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(3)) },
                    ButtonConfig("+", Orange, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Add)) }
                ),
                listOf(
                    ButtonConfig("AC", LightGray, 1f) { onAction(CalculatorAction.Clear) },
                    ButtonConfig("0", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(0)) },
                    ButtonConfig(".", Color.DarkGray, 1f) { onAction(CalculatorAction.Decimal) },
                    ButtonConfig("=", Orange, 1f) { onAction(CalculatorAction.Calculate) }
                )
            )

            buttonRows.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                ) {
                    row.forEach { buttonConfig ->
                        CalculatorButton(
                            symbol = buttonConfig.symbol,
                            modifier = Modifier
                                .background(buttonConfig.backgroundColor)
                                .aspectRatio(buttonConfig.aspectRatio)
                                .weight(buttonConfig.aspectRatio),
                            onClick = buttonConfig.onClick
                        )
                    }
                }
            }
        }
    }
}

