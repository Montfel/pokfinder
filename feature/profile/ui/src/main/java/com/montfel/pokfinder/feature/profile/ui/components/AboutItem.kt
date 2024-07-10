package com.montfel.pokfinder.feature.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.feature.profile.ui.model.AboutData

@Composable
fun AboutItem(data: AboutData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = data.title),
            style = PokfinderTheme.typography.pokemonType,
            color = PokfinderTheme.palette.primaryText,
            modifier = Modifier.width(100.dp)
        )

        Text(
            text = data.description,
            style = PokfinderTheme.typography.description,
            color = PokfinderTheme.palette.primaryVariantText
        )
    }

    Spacer(modifier = Modifier.height(16.dp))
}
