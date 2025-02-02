package com.example.calculator.Domain.Entities

sealed class CalculatorScientificOperation(val symbol : String) {
    data object Sin: CalculatorScientificOperation("sin(")
    data object Cos: CalculatorScientificOperation("cos(")
    data object Tg: CalculatorScientificOperation("tan(")
    data object Ctg: CalculatorScientificOperation("cot(")
    data object Sqrt: CalculatorScientificOperation("sqrt(")
    data object Log2: CalculatorScientificOperation("log2(")
    data object Log10: CalculatorScientificOperation("log10(")
    data object Ln: CalculatorScientificOperation("ln(")
    data object Abs: CalculatorScientificOperation("abs(")
    data object EPow: CalculatorScientificOperation("e^")
}