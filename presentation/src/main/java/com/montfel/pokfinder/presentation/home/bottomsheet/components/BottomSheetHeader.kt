package com.montfel.pokfinder.presentation.home.bottomsheet.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.montfel.pokfinder.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.designsystem.theme.primaryText
import com.montfel.pokfinder.designsystem.theme.primaryVariantText

@Composable
fun BottomSheetHeader(
    @StringRes title: Int,
    @StringRes description: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = title),
            style = PokfinderTheme.typography.pokemonName,
            color = MaterialTheme.colors.primaryText,
        )

        Text(
            text = stringResource(id = description),
            style = PokfinderTheme.typography.description,
            color = MaterialTheme.colors.primaryVariantText,
        )
    }
}
