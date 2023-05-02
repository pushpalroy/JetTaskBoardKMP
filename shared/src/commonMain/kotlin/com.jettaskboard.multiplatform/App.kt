package com.jettaskboard.multiplatform

import androidx.compose.runtime.Composable
import com.jettaskboard.multiplatform.ui.RootComponent
import com.jettaskboard.multiplatform.ui.theme.JtbTheme

@Composable
fun App(
    isExpandedScreen: Boolean = false
) {
    JtbTheme(darkTheme = true) {
        RootComponent(isExpandedScreen = isExpandedScreen)
    }
}

expect fun getPlatformName(): String