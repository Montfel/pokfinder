package com.montfel.pokfinder.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.montfel.pokfinder.designsystem.R

val font = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.Normal),
    Font(R.font.sf_pro_display_medium, FontWeight.Medium),
    Font(R.font.sf_pro_display_bold, FontWeight.Bold)
)

@Immutable
data class Typography(
    val title: TextStyle,
    val applicationTitle: TextStyle,
    val pokemonName: TextStyle,
    val filterTitle: TextStyle,
    val description: TextStyle,
    val pokemonNumber: TextStyle,
    val pokemonType: TextStyle,
)

internal val typography = Typography(
    title = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 100.sp
    ),
    applicationTitle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    pokemonName = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp
    ),
    filterTitle = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    description = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    pokemonNumber = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    ),
    pokemonType = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
)
