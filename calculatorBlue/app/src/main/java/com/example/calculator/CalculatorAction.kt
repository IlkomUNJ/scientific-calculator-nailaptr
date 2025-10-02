package com.example.calculator

sealed class CalculatorAction {
    data class Number(val number: Int): CalculatorAction()
    object Clear: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    object Percent: CalculatorAction()
    data class Function(val function: CalculatorFunction): CalculatorAction()
    data class ToggleMode(val mode: CalculatorMode): CalculatorAction()
    //calling calculator operation class
    data class Operation(val operation: CalculatorOperation): CalculatorAction()
}