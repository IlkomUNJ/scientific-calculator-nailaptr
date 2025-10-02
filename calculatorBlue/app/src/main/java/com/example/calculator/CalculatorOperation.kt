package com.example.calculator

sealed class CalculatorOperation(val symbol: String) {
    object Add: CalculatorOperation(symbol = "+")
    object Substract: CalculatorOperation(symbol = "-")
    object Multiply: CalculatorOperation(symbol = "×")
    object Divide: CalculatorOperation(symbol = "÷")

}