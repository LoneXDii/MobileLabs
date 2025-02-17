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


object Colors {
    var DefaultTextColor by mutableStateOf(Color.White)
    var SecondaryTextColor by mutableStateOf(Color.Green)
    var ScientificOperationsButtonColor by mutableStateOf(Color.Red)
    var OperationButtonColor by mutableStateOf(Color(0xFFFF9800)) // Orange
    var HelpActionButtonColor by mutableStateOf(Color(0xFF818181)) // Light Gray
    var NumericButtonColor by mutableStateOf(Color.DarkGray)
    var MainBackground by mutableStateOf(Color(0xFF2E2E2E)) // MainBackground
}

//var DefaultTextColor = Color.White
//var SecondaryTextColor = Color.Green
//var ScientificOperationsButtonColor = Color.Red
//var OperationButtonColor = Color(0xFFFF9800) //Orange
//var HelpActionButtonColor = Color(0xFF818181) //Light Gray
//var NumericButtonColor = Color.DarkGray
//var MainBackground = Color(0xFF2E2E2E) //MainBackground

