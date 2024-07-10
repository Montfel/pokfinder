package com.montfel.pokfinder.feature.home.ui.bottomsheet.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

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
            color = PokfinderTheme.palette.primaryText,
        )

        Text(
            text = stringResource(id = description),
            style = PokfinderTheme.typography.description,
            color = PokfinderTheme.palette.primaryVariantText,
        )
    }
}
