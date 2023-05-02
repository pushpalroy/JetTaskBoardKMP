package com.jettaskboard.multiplatform

import androidx.compose.runtime.Composable
import com.jettaskboard.multiplatform.ui.RootComponent
import com.jettaskboard.multiplatform.ui.theme.JtbTheme

@Composable
fun App() {
    JtbTheme(darkTheme = true) {
        RootComponent()
    }
}

expect fun getPlatformName(): String