package com.jettaskboard.multiplatform

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.jettaskboard.multiplatform.di.initKoin
import com.jettaskboard.multiplatform.util.krouter.LocalComponentContext
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController {

    initKoin()
    val lifecycle = LifecycleRegistry()

    val rootComponentContext = DefaultComponentContext(lifecycle = lifecycle)

    return ComposeUIViewController {
        CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
            App()
        }
    }
}