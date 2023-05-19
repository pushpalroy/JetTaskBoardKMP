package com.jettaskboard.multiplatform.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = medium_grey,
    primaryVariant = very_light_grey,
    secondary = green,
    background = dark_grey,
    surface = light_grey,
    error = dark_red,
    onPrimary = Color.White,
    onSecondary = dark_blue,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White,
)

private val LightColorPalette = lightColors(
    primary = medium_grey,
    primaryVariant = very_light_grey,
    secondary = green,
    background = dark_grey,
    surface = light_grey,
    error = dark_red,
    onPrimary = Color.White,
    onSecondary = dark_blue,
    onBackground = Color.White,
    onSurface = Color.White,
    onError = Color.White
)

@Composable
fun JtbTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
