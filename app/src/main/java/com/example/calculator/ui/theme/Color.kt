package com.example.calculator.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

var Purple80 = Color(0xFFD0BCFF)
var PurpleGrey80 = Color(0xFFCCC2DC)
var Pink80 = Color(0xFFEFB8C8)

var Purple40 = Color(0xFF6650a4)
var PurpleGrey40 = Color(0xFF625b71)
var Pink40 = Color(0xFF7D5260)


var DefaultDefaultTextColor = Color.White
var DefaultSecondaryTextColor = Color.Green
var DefaultScientificOperationsButtonColor = Color.Red
var DefaultOperationButtonColor = Color(0xFFFF9800) //Orange
var DefaultHelpActionButtonColor = Color(0xFF818181) //Light Gray
var DefaultNumericButtonColor = Color.DarkGray
var DefaultMainBackground = Color(0xFF2E2E2E) //MainBackground

object Colors {
    var DefaultTextColor by mutableStateOf(DefaultDefaultTextColor)
    var SecondaryTextColor by mutableStateOf(DefaultSecondaryTextColor)
    var ScientificOperationsButtonColor by mutableStateOf(DefaultScientificOperationsButtonColor)
    var OperationButtonColor by mutableStateOf(DefaultOperationButtonColor)
    var HelpActionButtonColor by mutableStateOf(DefaultHelpActionButtonColor)
    var NumericButtonColor by mutableStateOf(DefaultNumericButtonColor)
    var MainBackground by mutableStateOf(DefaultMainBackground)
}



