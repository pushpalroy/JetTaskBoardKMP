package com.jettaskboard.multiplatform.util.insetsx

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.runtime.Composable

/**
 * Copied from library: https://github.com/mori-atsushi/insetsx
 */
expect val WindowInsets.Companion.navigationBars: WindowInsets
    @Composable get

expect val WindowInsets.Companion.statusBars: WindowInsets
    @Composable get

expect val WindowInsets.Companion.systemBars: WindowInsets
    @Composable get

@ExperimentalSoftwareKeyboardApi
expect val WindowInsets.Companion.ime: WindowInsets
    @Composable get

@ExperimentalSoftwareKeyboardApi
expect val WindowInsets.Companion.safeDrawing: WindowInsets
    @Composable get
