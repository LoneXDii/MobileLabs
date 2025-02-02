package com.example.calculator.Application.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorOperation
import com.example.calculator.Domain.Entities.CalculatorState
import net.objecthunter.exp4j.ExpressionBuilder

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
        if (state.expression.isNotBlank()) {
            state = state.copy(
                expression = state.expression.dropLast(1)
                //Add check for operation here
            )
        }

        calculateTemporaryResult()
    }

    private fun performCalculation() {
        val result = calculate() ?: return

        state = state.copy(
            expression = result,
            operation = null,
            tempResult = ""
        )
    }

    private fun enterOperation(operation: CalculatorOperation) {
        if(state.expression.isNotBlank()){
            state = state.copy(
                operation = operation,
                expression = state.expression + operation.symbol
            )

            calculateTemporaryResult()
        }
    }

    private fun enterDecimal() {
        state = state.copy(
            expression = state.expression + "."
        )
    }

    private fun enterNumber(number: Int) {
        state = state.copy(
            expression = state.expression + number.toString()
        )

        calculateTemporaryResult()
    }

    private fun calculateTemporaryResult(){
        var result = calculate()

        if(result != null){
            state = state.copy(
                tempResult = formatNumber(result, 10)
            )
        }
        else{
            state = state.copy(
                tempResult = ""
            )
        }
    }

    private fun calculate(): String?{
        if (state.expression != ""){
            var result = "";

            try {
                var expr = ExpressionBuilder(state.expression).build()
                val res = expr.evaluate()
                val longres = res.toLong()

                if (longres.toDouble() == res) {
                    result = longres.toString()
                } else {
                    result = res.toString()
                }
            } catch (e: Exception) {
                result = "Error"
            }

            return result
        }

        return null
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