package com.example.calculator.Presentation.Pages.Calculator.Components.ScientificCalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorConstants
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Domain.Entities.CalculatorScientificOperation
import com.example.calculator.Presentation.Pages.Calculator.Components.ButtonConfig
import com.example.calculator.Presentation.Pages.Calculator.Components.CalculatorButton
import com.example.calculator.ui.theme.Colors

@Composable
fun ScientificCalculatorButtons(
    onAction: (CalculatorAction) -> Unit,
    buttonSpacing: Dp = 8.dp
){
    val buttonRows = listOf(
        listOf(
            ButtonConfig("sin", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Sin)) },
            ButtonConfig("cos", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Cos)) },
            ButtonConfig("log2", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Log2)) },
            ButtonConfig("(", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Bracket(true)) },
            ButtonConfig(")", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Bracket(false)) },
            ButtonConfig("Del", Colors.HelpActionButtonColor, 1f) { onAction(CalculatorAction.Delete) },
            ButtonConfig("/", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.Divide)) }
        ),
        listOf(
            ButtonConfig("tg", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Tg)) },
            ButtonConfig("ctg", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Ctg)) },
            ButtonConfig("ln", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Ln)) },
            ButtonConfig("7", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(7)) },
            ButtonConfig("8", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(8)) },
            ButtonConfig("9", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(9)) },
            ButtonConfig("*", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.Multiply)) }
        ),
        listOf(
            ButtonConfig("|x|", Colors.ScientificOperationsButtonColor, 1f) {onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Abs)) },
            ButtonConfig("x^y", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.Power)) },
            ButtonConfig("log10", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Log10)) },
            ButtonConfig("4", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(4)) },
            ButtonConfig("5", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(5)) },
            ButtonConfig("6", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(6)) },
            ButtonConfig("-", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.Subtract)) }
        ),
        listOf(
            ButtonConfig("sqrt", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.Sqrt)) },
            ButtonConfig("1/x", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.OneDivX)) },
            ButtonConfig("x^2", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.Pow2)) },
            ButtonConfig("1", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(1)) },
            ButtonConfig("2", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(2)) },
            ButtonConfig("3", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(3)) },
            ButtonConfig("+", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Operation(
                CalculatorOperation.Add)) }
        ),
        listOf(
            ButtonConfig("e", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.Constant(
                CalculatorConstants.E)) },
            ButtonConfig("pi", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.Constant(
                CalculatorConstants.Pi)) },
            ButtonConfig("e^x", Colors.ScientificOperationsButtonColor, 1f) { onAction(CalculatorAction.ScientificOperation(
                CalculatorScientificOperation.EPow)) },
            ButtonConfig("AC", Colors.HelpActionButtonColor, 1f) { onAction(CalculatorAction.Clear) },
            ButtonConfig("0", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Number(0)) },
            ButtonConfig(".", Colors.NumericButtonColor, 1f) { onAction(CalculatorAction.Decimal) },
            ButtonConfig("=", Colors.OperationButtonColor, 1f) { onAction(CalculatorAction.Calculate) }
        )
    )

    buttonRows.forEach { row ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
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