package com.cs4520.assignment3.presenters

import com.cs4520.assignment3.models.OperationResult
import com.cs4520.assignment3.views.CalculatorView

class CalculatorPresenter(
    private val view: CalculatorView
) {

    fun add(field1: Int?, field2: Int?) {
        conductOperation(field1, field2) { n1, n2 -> OperationResult((n1 + n2).toDouble(), null)}
    }

    fun subtract(field1: Int?, field2: Int?) {
        conductOperation(field1, field2) { n1, n2 -> OperationResult((n1 - n2).toDouble(), null)}
    }

    fun multiply(field1: Int?, field2: Int?) {
        conductOperation(field1, field2) { n1, n2 -> OperationResult((n1 * n2).toDouble(), null)}
    }

    fun divide(field1: Int?, field2: Int?) {
        conductOperation(field1, field2) { n1, n2 ->
            if (n2 == 0)
                OperationResult(null, "Divide by 0 error")
            else
                OperationResult((n1 * n2).toDouble(), null)
        }
    }

    private fun conductOperation(field1: Int?, field2: Int?, f: (n1: Int, n2: Int) -> OperationResult) {
        this.view.clearFields()

        if (field1 == null || field2 == null) {
            this.view.sendToastMessage("Make sure both fields have input numbers");
            return;
        }

        val result = f(field1, field2);
        if (result.output != null) {
            this.view.setResult(result.output)
        }
        else {
            if (result.err != null) {
                this.view.sendToastMessage(result.err);
            }
            else {
                this.view.sendToastMessage("Unknown invalid mathematical operation");
            }
        }
    }

}