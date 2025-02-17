package com.example.calculator.Presentation.Pages.Calculator.Components.BaseCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Presentation.Pages.Calculator.Components.ButtonConfig
import com.example.calculator.Presentation.Pages.Calculator.Components.CalculatorButton
import com.example.calculator.ui.theme.Colors

@Composable
fun BaseCalculatorButtons(
    onAction: (CalculatorAction) -> Unit,
    buttonSpacing: Dp = 8.dp
){
    val buttonRows = listOf(
        listOf(
            ButtonConfig("(", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Bracket(true)) },
            ButtonConfig(")", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Bracket(false)) },
            ButtonConfig("Del", Colors.HelpActionButtonColor, 1f) { onAction(CalculatorAction.Delete) },
            ButtonConfig("/", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Divide)) }
        ),
        listOf(
            ButtonConfig("7", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(7)) },
            ButtonConfig("8", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(8)) },
            ButtonConfig("9", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(9)) },
            ButtonConfig("*", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Multiply)) }
        ),
        listOf(
            ButtonConfig("4", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(4)) },
            ButtonConfig("5", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(5)) },
            ButtonConfig("6", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(6)) },
            ButtonConfig("-", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Subtract)) }
        ),
        listOf(
            ButtonConfig("1", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(1)) },
            ButtonConfig("2", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(2)) },
            ButtonConfig("3", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(3)) },
            ButtonConfig("+", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(CalculatorOperation.Add)) }
        ),
        listOf(
            ButtonConfig("AC", Colors.HelpActionButtonColor, 1f) { onAction(CalculatorAction.Clear) },
            ButtonConfig("0", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(0)) },
            ButtonConfig(".", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Decimal) },
            ButtonConfig("=", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Calculate) }
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