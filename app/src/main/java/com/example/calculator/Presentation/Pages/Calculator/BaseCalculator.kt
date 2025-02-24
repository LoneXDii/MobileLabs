package com.example.calculator.Presentation.Pages.Calculator

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.calculator.Domain.Entities.CalculatorAction
import com.example.calculator.Domain.Entities.CalculatorState
import com.example.calculator.Presentation.Pages.Calculator.Components.BaseCalculator.BaseCalculatorButtons
import com.example.calculator.Presentation.Pages.Calculator.Components.BaseCalculator.BaseCalculatorOutput
import com.example.calculator.Presentation.Pages.Customization.ColorsSettings
import com.example.calculator.Presentation.Pages.History.CalculatorHistory
import com.example.calculator.ui.theme.Colors

@Composable
fun BaseCalculator(
    state: CalculatorState,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (CalculatorAction) -> Unit,
    onCameraButton: () -> Unit,
    onSetValue: (String) -> Unit,
    isAuthorized: Boolean,
    navController: NavController
) {
    val isHistoryOpen = remember { mutableStateOf(false) }
    val isSettingOpen = remember { mutableStateOf(false) }
    var isSwiping = false

    Box(
        modifier = modifier
            .pointerInput(Unit){
                detectHorizontalDragGestures(
                    onHorizontalDrag = { change, dragAmount ->
                        if (dragAmount < -75 && !isSwiping) {
                            isSwiping = true
                            onAction(CalculatorAction.Delete)
                            change.consume()
                        }
                    },
                    onDragEnd = {
                        isSwiping = false
                    }
                )
            }
    ){
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.Center
        ) {
            BaseCalculatorOutput(state)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.Start
            ){
                Row(
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(
                        onClick = {
                            isHistoryOpen.value = !isHistoryOpen.value
                            isSettingOpen.value = false
                        },
                        enabled = isAuthorized
                    ) {
                        Icon(
                            imageVector = if (isHistoryOpen.value) Icons.Default.Calculate else Icons.Default.History,
                            contentDescription = "History",
                            tint = Colors.DefaultTextColor,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(36.dp)
                                .alpha(if (isAuthorized) 1f else 0.2f)
                        )
                    }

                    IconButton(
                        onClick = onCameraButton,
                        enabled = isAuthorized
                    ) {
                        Icon(
                            imageVector = Icons.Default.Camera,
                            contentDescription = "Open Camera",
                            tint = Colors.DefaultTextColor,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(36.dp)
                                .alpha(if (isAuthorized) 1f else 0.2f)
                        )
                    }

                    IconButton(
                        onClick = {
                            isSettingOpen.value = !isSettingOpen.value
                            isHistoryOpen.value = false
                        },
                        enabled = isAuthorized
                    ) {
                        Icon(
                            imageVector = if (isSettingOpen.value) Icons.Default.Calculate else Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Colors.DefaultTextColor,
                            modifier = Modifier
                                .padding(2.dp)
                                .size(36.dp)
                                .alpha(if (isAuthorized) 1f else 0.2f)
                        )
                    }

                    if (!isAuthorized) {
                        IconButton(
                            onClick = {
                                navController.navigate("login") {
                                    popUpTo("main/false") { inclusive = true }
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Login,
                                contentDescription = "Login",
                                tint = Colors.DefaultTextColor,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .size(36.dp)
                            )
                        }
                    }
                    else {
                        IconButton(
                            onClick = {
                                navController.navigate("changePassword") {
                                    popUpTo("main/false") { inclusive = false }
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountBox,
                                contentDescription = "Login",
                                tint = Colors.DefaultTextColor,
                                modifier = Modifier
                                    .padding(2.dp)
                                    .size(36.dp)
                            )
                        }
                    }
                }
            }

            Column(
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(buttonSpacing))
            {
                if(isHistoryOpen.value) {
                    CalculatorHistory(onSetValue)
                }
                else if(isSettingOpen.value){
                    ColorsSettings()
                }
                else{
                    BaseCalculatorButtons(onAction, buttonSpacing)
                }
            }
        }
    }
}

