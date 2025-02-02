package com.example.calculator.Domain.Entities

sealed class CalculatorOperation(val symbol : String) {
    data object Add: CalculatorOperation("+")
    data object Subtract: CalculatorOperation("-")
    data object Multiply: CalculatorOperation("*")
    data object Divide: CalculatorOperation("/")
    data object Power: CalculatorOperation("^")
    data object OneDivX: CalculatorOperation("*(1/")
    data object Pow2: CalculatorOperation("^2")
}