package com.example.calculator.Application.ViewModel

import android.os.Vibrator
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CalculatorViewModelFactory(
    private val vibrator: Vibrator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        return if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            CalculatorViewModel(vibrator) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}