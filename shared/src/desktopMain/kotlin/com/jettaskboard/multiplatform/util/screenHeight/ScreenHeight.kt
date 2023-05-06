package com.jettaskboard.multiplatform.util.screenHeight

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState

@Composable
actual fun screenHeight(): Float {
    val windowState = rememberWindowState(size = DpSize(1200.dp, 800.dp))
    return windowState.size.height.value
}