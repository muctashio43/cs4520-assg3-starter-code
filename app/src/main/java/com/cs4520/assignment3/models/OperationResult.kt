package com.cs4520.assignment3.models

// Represents the result of a math operation, which may result in an error message.
// Such as a divide by 0 error.
data class OperationResult(val output: Double?, val err: String?)