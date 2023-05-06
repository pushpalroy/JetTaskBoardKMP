package com.jettaskboard.multiplatform.util.screenHeight

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
actual fun screenHeight(): Float = LocalConfiguration.current.screenHeightDp.toFloat()