package com.montfel.pokfinder.feature.profile.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun AboutTypeItem(@StringRes title: Int, typesList: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(id = title),
            style = PokfinderTheme.typography.pokemonType,
            color = PokfinderTheme.palette.primaryText,
            modifier = Modifier.width(100.dp)
        )

        if (typesList.isEmpty()) {
            Text(
                text = stringResource(id = R.string.none),
                style = PokfinderTheme.typography.description,
                color = PokfinderTheme.palette.primaryVariantText
            )
        } else {
            typesList.forEach {
                TypeEffectivenessItem(typeName = it)
            }
        }
    }
}
