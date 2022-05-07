package com.montfel.pokedex.presentation.theme

import androidx.compose.material.Typography
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

val Typography = Typography(
    h1 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Bold,
    ),
    h2 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Medium,
    ),
    h3 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
    ),
    h4 = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

)
