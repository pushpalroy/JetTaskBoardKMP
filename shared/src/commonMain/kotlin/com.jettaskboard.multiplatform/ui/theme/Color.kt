package com.jettaskboard.multiplatform.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val drawer_dark_surface = Color(0xFF2C2C2E)
val divider_color = Color(0xFF1B1B1D)
val DefaultTaskBoardBGColor = Color(0xFF0079bf)

val dark_grey = Color(0xFF222222)
val medium_grey = Color(0xFF28312e)
val light_grey = Color(0xFF2a2a2a)
val very_light_grey = Color(0xFF444444)
val green = Color(0xFF64bd53)
val dark_blue = Color(0xFF12274a)
val dark_red = Color(0xFF690005)

val MotionTopBarColor
    @Composable get() =
        if (isSystemInDarkTheme()) light_grey else Color.White
