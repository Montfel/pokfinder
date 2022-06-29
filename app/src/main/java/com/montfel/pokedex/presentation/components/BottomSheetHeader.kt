package com.montfel.pokedex.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.montfel.pokedex.presentation.theme.description
import com.montfel.pokedex.presentation.theme.pokemonName
import com.montfel.pokedex.presentation.theme.primaryText
import com.montfel.pokedex.presentation.theme.primaryVariantText

@Composable
fun BottomSheetHeader(
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.pokemonName,
            color = MaterialTheme.colors.primaryText,
        )
        Text(
            text = stringResource(id = description),
            style = MaterialTheme.typography.description,
            color = MaterialTheme.colors.primaryVariantText,
        )
    }
}
