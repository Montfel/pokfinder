package com.montfel.pokedex.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.montfel.pokedex.R

val font = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_bold, FontWeight.Bold)
)

val Typography.title: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 100.sp
    )

val Typography.applicationTitle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )

val Typography.pokemonName: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    )

val Typography.filterTitle: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

val Typography.description: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

val Typography.pokemonNumber: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )

val Typography.pokemonType: TextStyle
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    )
