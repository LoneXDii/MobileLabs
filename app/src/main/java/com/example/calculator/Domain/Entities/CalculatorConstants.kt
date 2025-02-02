package com.example.calculator.Domain.Entities

sealed class CalculatorConstants(val value: Double) {
    data object E: CalculatorConstants(2.71828182)
    data object Pi: CalculatorConstants(3.14159265)
}