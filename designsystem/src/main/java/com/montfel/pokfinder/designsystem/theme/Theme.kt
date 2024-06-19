package com.montfel.pokfinder.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import com.montfel.pokfinder.designsystem.theme.Typography as PokfinderTypography


val LightColorPalette = lightColors(
    onSurface = Gray17.copy(alpha = 0.5f),
)

val DarkColorPalette = darkColors(
    onSurface = Gray17.copy(alpha = 0.5f),
)

private val LocalTypography = staticCompositionLocalOf {
    PokfinderTypography(
        title = TextStyle.Default,
        applicationTitle = TextStyle.Default,
        pokemonName = TextStyle.Default,
        filterTitle = TextStyle.Default,
        description = TextStyle.Default,
        pokemonNumber = TextStyle.Default,
        pokemonType = TextStyle.Default,
    )
}

@Composable
fun PokfinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(LocalTypography provides typography) {
        MaterialTheme(
            colors = colors,
            shapes = Shapes,
            content = content
        )
    }
}

object PokfinderTheme {
    val typography: PokfinderTypography @Composable get() = LocalTypography.current
}

