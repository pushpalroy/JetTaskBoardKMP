package com.jettaskboard.multiplatform

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.jettaskboard.multiplatform.ui.screens.board.TaskBoardRoute

@Composable
fun App() {
    MaterialTheme {
        TaskBoardRoute()
    }
}

expect fun getPlatformName(): String