package com.montfel.pokedex.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.montfel.pokedex.helper.AssetHelper

val LocalAssetHelper = staticCompositionLocalOf<AssetHelper> { error("No asset helper provided") }

val LightColorPalette = lightColors(
    onSurface = Gray17.copy(alpha = 0.5f),
)

val DarkColorPalette = darkColors(
    onSurface = Gray17.copy(alpha = 0.5f),
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = colors.systemBar,
            darkIcons = !darkTheme
        )
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}
