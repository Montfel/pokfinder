package com.montfel.pokfinder.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.montfel.pokfinder.core.designsystem.theme.Typography as PokfinderTypography

@Composable
fun PokfinderTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val palette = if (darkTheme) darkPalette else lightPalette

    CompositionLocalProvider(
        LocalTypography provides typography,
        LocalPalette provides palette
    ) {
        MaterialTheme(content = content)
    }
}

object PokfinderTheme {
    val typography: PokfinderTypography @Composable get() = LocalTypography.current
    val palette: Palette @Composable get() = LocalPalette.current
}

private val LocalTypography = staticCompositionLocalOf { PokfinderTypography() }
private val LocalPalette = staticCompositionLocalOf { Palette() }
