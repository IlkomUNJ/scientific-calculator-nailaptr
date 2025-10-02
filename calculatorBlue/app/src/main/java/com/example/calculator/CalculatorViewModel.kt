package com.example.calculator

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction){
        when(action){
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorState()
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Calculate -> performCalculation()
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Percent -> applyPercent()
            is CalculatorAction.Function -> applyFunction(action.function)
            is CalculatorAction.ToggleMode -> toggleMode(action.mode)
        }
    }

    private fun performDeletion(){
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation != null -> state = state.copy (
                operation = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation(){
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null){
            val result = when(state.operation) {
                is CalculatorOperation.Add -> number1 + number2
                is CalculatorOperation.Substract -> number1 - number2
                is CalculatorOperation.Multiply -> number1 * number2
                is CalculatorOperation.Divide -> number1 / number2
                null -> return
            }
            state = state.copy (
                number1 = result.toString().take(15),
                number2 = "",
                operation = null
            )
        }
    }

    private fun enterOperation(operation: CalculatorOperation){
        if (state.number1.isNotBlank()){
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal(){
        if(state.operation == null && !state.number1.contains(".")
            && state.number1.isNotBlank())
        {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if(!state.number2.contains(".") && state.number2.isNotBlank())
        {
            state = state.copy(
                number2 = state.number2 + "."
            )
            return
        }
    }

    private fun enterNumber(number: Int){
        if(state.operation == null){
            if(state.number1.length >= MAX_NUM_LENGTH){
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= MAX_NUM_LENGTH){
            return
        }
        state = state.copy (
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

    private fun toggleMode(mode: CalculatorMode) {
        state = state.copy(mode = mode)
    }

    private fun applyPercent() {
        if (state.operation == null) {
            val value = state.number1.toDoubleOrNull() ?: return
            state = state.copy(number1 = (value / 100.0).toString().take(15))
        } else {
            val number1 = state.number1.toDoubleOrNull() ?: return
            val number2 = state.number2.toDoubleOrNull() ?: return
            val percentOfFirst = number1 * (number2 / 100.0)
            state = state.copy(number2 = percentOfFirst.toString().take(15))
        }
    }

    private fun applyFunction(function: CalculatorFunction) {
        // Apply to the active operand: prefer number2 if present, else number1
        val applyToSecond = state.operation != null && state.number2.isNotBlank()
        val src = if (applyToSecond) state.number2 else state.number1
        val value = src.toDoubleOrNull() ?: return

        val result = when (function) {
            CalculatorFunction.Reciprocal -> if (value == 0.0) return else 1.0 / value
            CalculatorFunction.Factorial -> {
                if (value < 0 || value % 1.0 != 0.0) return
                factorial(value.toLong()).toDouble()
            }
            CalculatorFunction.Sqrt -> if (value < 0) return else kotlin.math.sqrt(value)
            CalculatorFunction.Sin -> kotlin.math.sin(Math.toRadians(value))
            CalculatorFunction.Cos -> kotlin.math.cos(Math.toRadians(value))
            CalculatorFunction.Tan -> kotlin.math.tan(Math.toRadians(value))
            CalculatorFunction.ASin -> Math.toDegrees(kotlin.math.asin(value))
            CalculatorFunction.ACos -> Math.toDegrees(kotlin.math.acos(value))
            CalculatorFunction.ATan -> Math.toDegrees(kotlin.math.atan(value))
            CalculatorFunction.Log10 -> if (value <= 0) return else kotlin.math.log10(value)
            CalculatorFunction.Ln -> if (value <= 0) return else kotlin.math.ln(value)
        }

        if (applyToSecond) {
            state = state.copy(number2 = result.toString().take(15))
        } else {
            state = state.copy(number1 = result.toString().take(15))
        }
    }

    private fun factorial(n: Long): Long {
        var acc = 1L
        var i = 2L
        while (i <= n) {
            acc *= i
            i++
        }
        return acc
    }
}
