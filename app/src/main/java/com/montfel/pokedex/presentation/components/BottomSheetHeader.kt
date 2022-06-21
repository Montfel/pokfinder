package com.montfel.pokedex.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.montfel.pokedex.presentation.theme.Gray17
import com.montfel.pokedex.presentation.theme.Gray74
import com.montfel.pokedex.presentation.theme.description
import com.montfel.pokedex.presentation.theme.pokemonName

@Composable
fun BottomSheetHeader(
    @StringRes title: Int,
    @StringRes description: Int
) {
    Column() {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.pokemonName,
            color = Gray17,
        )
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.description,
            color = Gray74,
        )
    }
}
