package com.example.calculator.Domain.Entities

data class CalculatorState(
    val expression: String = "",
    val operation: CalculatorOperation? = null,
    val tempResult: String = ""
)
