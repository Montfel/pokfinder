package com.montfel.pokfinder.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PokfinderTheme.palette.background)
    ) {
        CircularProgressIndicator(
            color = PokfinderTheme.palette.primaryInput,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
