package com.example.calculator.Presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {
    Box(modifier = modifier){
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
                    text = state.number1 + (state.operation?.symbol ?: "") + state.number2,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 35.sp,
                    lineHeight = 40.sp,
                    color = Color.White,
                    maxLines = 3
                )

                Text(
                    text = state.tempResult,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 55.sp,
                    lineHeight = 65.sp,
                    color = Color.White,
                    maxLines = 1
                )
            }

            val buttonRows = listOf(
                listOf(
                    ButtonConfig("AC", LightGray, 2f) { onAction(CalculatorAction.Clear) },
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
                    ButtonConfig("0", Color.DarkGray, 2f) { onAction(CalculatorAction.Number(0)) },
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

data class ButtonConfig(
    val symbol: String,
    val backgroundColor: Color,
    val aspectRatio: Float,
    val onClick: () -> Unit
)