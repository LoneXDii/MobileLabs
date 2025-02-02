package com.example.calculator.Domain.Entities

sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    data class Bracket(val isOpen: Boolean) : CalculatorAction()
    data object Clear: CalculatorAction()
    data object Delete : CalculatorAction()
    data object Decimal : CalculatorAction()
    data object Calculate : CalculatorAction()
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
    data class ScientificOperation(val operation: CalculatorScientificOperation): CalculatorAction()
    data class Constant(val value: CalculatorConstants): CalculatorAction()
}