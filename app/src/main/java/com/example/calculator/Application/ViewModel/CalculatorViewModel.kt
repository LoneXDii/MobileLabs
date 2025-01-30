package com.example.calculator.Application.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Domain.Entities.CalculatorState

class CalculatorViewModel: ViewModel() {
    private var _state = mutableStateOf(CalculatorState())
    var state: CalculatorState
        get() = _state.value
        private set(value) {
            _state.value = value
        }

    fun onAction(action: CalculatorAction){
        when(action){
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when{
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )

            state.operation != null -> state = state.copy(
                operation = null
            )

            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }

        calculateTemporaryResult()
    }

    private fun performCalculation() {
        val result = calculate() ?: return

        state = state.copy(
            number1 = formatNumber(result.toString(), 15),
            number2 = "",
            operation = null,
            tempResult = ""
        )
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.number1.isNotBlank()){
            state = state.copy(operation = operation)

            calculateTemporaryResult()
        }
    }

    private fun enterDecimal() {
        if(state.operation == null
           && !state.number1.contains(".")
           && state.number1.isNotBlank())
        {
            state = state.copy(
                number1 =  state.number1 + "."
            )

            return
        }

        if(!state.number2.contains(".")
            && state.number2.isNotBlank())
        {
            state = state.copy(
                number2 =  state.number2 + "."
            )

            return
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operation == null){
            if(state.number1.length >= MAX_NUM_LENGTH){
                return
            }

            if(state.number1.startsWith("0") && state.number1.length <= 1){
                state = state.copy(
                    number1 = ""
                )
            }

            state = state.copy(
                number1 = state.number1 + number
            )

            return
        }

        if(state.number2.length >= MAX_NUM_LENGTH){
            return
        }

        if(state.number2.startsWith("0") && state.number2.length <= 1){
            state = state.copy(
                number2 = ""
            )
        }

        state = state.copy(
            number2 = state.number2 + number
        )

        calculateTemporaryResult()
    }

    private fun calculateTemporaryResult(){
        var result = calculate()

        if(result != null){
            state = state.copy(
                tempResult = formatNumber(result.toString(), 10)
            )
        }
        else{
            state = state.copy(
                tempResult = ""
            )
        }
    }

    private fun calculate(): Double?{
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()

        if(number1 == null || number2 == null){
            return null
        }

        val result = when(state.operation){
            is CalculatorOperation.Add -> number1 + number2
            is CalculatorOperation.Subtract -> number1 - number2
            is CalculatorOperation.Multiply -> number1 * number2
            is CalculatorOperation.Divide -> number1 / number2
            null -> return null
        }

        return result
    }

    private fun formatNumber(number: String, len: Int): String{
        val parts = number.split("E")

        if(parts.size < 2){
            return parts[0].take(len)
        }

        val eLen = parts[1].length + 1
        val firstPart = parts[0].take(len - eLen)

        return firstPart + "E" + parts[1]
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}