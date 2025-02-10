package com.example.calculator.Presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.calculator.Domain.Entities.CalculatorConstants
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Domain.Entities.CalculatorScientificOperation
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.ui.theme.LightGray
import com.example.calculator.ui.theme.Orange

@Composable
fun ScientificCalculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit
) {
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
                    fontSize = 30.sp,
                    lineHeight = 35.sp,
                    color = Color.White,
                    maxLines = 2
                )

                Text(
                    text = state.tempResult,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    lineHeight = 25.sp,
                    color = Color.White,
                    maxLines = 1
                )
            }

            val buttonRows = listOf(
                listOf(
                    ButtonConfig("sin", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Sin)) },
                    ButtonConfig("cos", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Cos)) },
                    ButtonConfig("log2", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Log2)) },
                    ButtonConfig("(", Orange, 1f) { onAction(CalculatorAction.Bracket(true)) },
                    ButtonConfig(")", Orange, 1f) { onAction(CalculatorAction.Bracket(false)) },
                    ButtonConfig("Del", LightGray, 1f) { onAction(CalculatorAction.Delete) },
                    ButtonConfig("/", Orange, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.Divide)) }
                ),
                listOf(
                    ButtonConfig("tg", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Tg)) },
                    ButtonConfig("ctg", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Ctg)) },
                    ButtonConfig("ln", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Ln)) },
                    ButtonConfig("7", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(7)) },
                    ButtonConfig("8", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(8)) },
                    ButtonConfig("9", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(9)) },
                    ButtonConfig("*", Orange, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.Multiply)) }
                ),
                listOf(
                    ButtonConfig("|x|", Color.Red, 1f) {onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Abs)) },
                    ButtonConfig("x^y", Color.Red, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.Power)) },
                    ButtonConfig("log10", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Log10)) },
                    ButtonConfig("4", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(4)) },
                    ButtonConfig("5", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(5)) },
                    ButtonConfig("6", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(6)) },
                    ButtonConfig("-", Orange, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.Subtract)) }
                ),
                listOf(
                    ButtonConfig("sqrt", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.Sqrt)) },
                    ButtonConfig("1/x", Color.Red, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.OneDivX)) },
                    ButtonConfig("x^2", Color.Red, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.Pow2)) },
                    ButtonConfig("1", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(1)) },
                    ButtonConfig("2", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(2)) },
                    ButtonConfig("3", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(3)) },
                    ButtonConfig("+", Orange, 1f) { onAction(CalculatorAction.Operation(
                        CalculatorOperation.Add)) }
                ),
                listOf(
                    ButtonConfig("e", Color.Red, 1f) { onAction(CalculatorAction.Constant(
                        CalculatorConstants.E)) },
                    ButtonConfig("pi", Color.Red, 1f) { onAction(CalculatorAction.Constant(
                        CalculatorConstants.Pi)) },
                    ButtonConfig("e^x", Color.Red, 1f) { onAction(CalculatorAction.ScientificOperation(
                        CalculatorScientificOperation.EPow)) },
                    ButtonConfig("AC", LightGray, 1f) { onAction(CalculatorAction.Clear) },
                    ButtonConfig("0", Color.DarkGray, 1f) { onAction(CalculatorAction.Number(0)) },
                    ButtonConfig(".", Color.DarkGray, 1f) { onAction(CalculatorAction.Decimal) },
                    ButtonConfig("=", Orange, 1f) { onAction(CalculatorAction.Calculate) }
                )
            )

            buttonRows.forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                ) {
                    row.forEach { buttonConfig ->
                        CalculatorButton(
                            symbol = buttonConfig.symbol,
                            modifier = Modifier
                                .background(buttonConfig.backgroundColor)
                                .aspectRatio(buttonConfig.aspectRatio * 3)
                                .weight(buttonConfig.aspectRatio * 0.5f),
                            onClick = buttonConfig.onClick,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}