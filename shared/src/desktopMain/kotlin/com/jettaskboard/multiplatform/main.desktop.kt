package com.jettaskboard.multiplatform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.jettaskboard.multiplatform.util.krouter.LocalComponentContext

@Composable
fun MainView(){
    val lifecycle = LifecycleRegistry()
    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)
    CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
        App(isExpandedScreen = true)
    }
}

actual fun getPlatformName(): String = "Desktop"