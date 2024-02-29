package com.cs4520.assignment3.views

interface CalculatorView {
    fun clearFields()
    fun setResult(n: Double?)
    fun sendToastMessage(msg: String)
}
