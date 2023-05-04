package com.jettaskboard.multiplatform

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.jettaskboard.multiplatform.di.initKoin
import com.jettaskboard.multiplatform.util.krouter.LocalComponentContext

fun main() = application {

    initKoin()
    val lifecycle = LifecycleRegistry()

    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    Window(
        title = "JetTaskBoardKMP",
        state = rememberWindowState(width = 1200.dp, height = 800.dp),
        onCloseRequest = ::exitApplication,
    ) {
        CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
            App(isExpandedScreen = true)
        }
    }
}

actual fun getPlatformName(): String = "Desktop"