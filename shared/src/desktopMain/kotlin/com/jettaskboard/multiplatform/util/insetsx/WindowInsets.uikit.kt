package com.jettaskboard.multiplatform.util.insetsx

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable

/**
 * Copied from library: https://github.com/mori-atsushi/insetsx
 */
actual val WindowInsets.Companion.navigationBars: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = WindowInsets(0, 0, 0, 0)

actual val WindowInsets.Companion.statusBars: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = WindowInsets(0, 0, 0, 0)

actual val WindowInsets.Companion.systemBars: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = WindowInsets(0, 0, 0, 0)

@ExperimentalSoftwareKeyboardApi
actual val WindowInsets.Companion.ime: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = WindowInsets(0, 0, 0, 0)

@ExperimentalSoftwareKeyboardApi
actual val WindowInsets.Companion.safeDrawing: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = WindowInsets(0, 0, 0, 0)
