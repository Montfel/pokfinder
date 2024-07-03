package com.montfel.pokfinder.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.core.designsystem.theme.primaryText
import com.montfel.pokfinder.core.designsystem.theme.primaryVariantText

@Composable
fun HomeHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = PokfinderTheme.typography.applicationTitle,
            color = MaterialTheme.colors.primaryText
        )

        Text(
            text = stringResource(id = R.string.subtitle),
            style = PokfinderTheme.typography.description,
            color = MaterialTheme.colors.primaryVariantText
        )
    }
}
