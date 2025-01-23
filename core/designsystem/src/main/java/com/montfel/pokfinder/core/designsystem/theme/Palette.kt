package com.montfel.pokfinder.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Palette(
    val primaryText: Color = Color.Unspecified,
    val primaryVariantText: Color = Color.Unspecified,
    val secondaryText: Color = Color.White,
    val primaryInput: Color = TypePsychic,
    val secondaryInput: Color = Color.Unspecified,
    val secondaryVariantInput: Color = GrayE2,
    val primaryIcon: Color = Color.White,
    val numberOverBackgroundColor: Color =  Gray17.copy(alpha = 0.6f),
    val pokeballIcon: Color = Color.Unspecified,
    val primaryPattern: Color = White10,
    val secondaryPattern: Color = Color.Unspecified,
    val topBarIcon: Color = Color.Unspecified,
    val fabBackground: Color = Color.Unspecified,
    val fabContent: Color = Color.Unspecified,
    val systemBar: Color = Color.Unspecified,
    val textFieldIcon: Color = Gray74,
    val background: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Gray17.copy(alpha = 0.5f)
)

internal val lightPalette = Palette(
    primaryText = Gray17,
    primaryVariantText = Gray74,
    secondaryInput = GrayF2,
    pokeballIcon = GrayF5,
    secondaryPattern = GrayEC,
    topBarIcon = Gray12,
    fabBackground = Color.White,
    fabContent = Gray12,
    systemBar = Color.White,
    background = Color.White,
    surface = Color.White,
)

internal val darkPalette = Palette(
    primaryText = White87,
    primaryVariantText = White60,
    secondaryInput = Gray20,
    pokeballIcon = Gray20,
    secondaryPattern = Gray20,
    topBarIcon = White87,
    fabBackground = Gray12,
    fabContent = Color.White,
    systemBar = Color.Black,
    background = Gray12,
    surface = Gray12
)
