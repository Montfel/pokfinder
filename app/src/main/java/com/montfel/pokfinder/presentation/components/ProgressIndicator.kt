package com.montfel.pokfinder.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.montfel.pokfinder.presentation.theme.primaryInput

@Composable
fun ProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colors.primaryInput,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
