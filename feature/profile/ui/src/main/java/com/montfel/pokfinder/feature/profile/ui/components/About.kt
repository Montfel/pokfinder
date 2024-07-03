package com.montfel.pokfinder.feature.profile.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.core.designsystem.theme.primaryVariantText
import com.montfel.pokfinder.feature.profile.ui.model.AboutData

@Composable
fun About(
    flavorText: String?,
    pokedexData: List<AboutData>,
    training: List<AboutData>,
    breeding: List<AboutData>,
    typeColor: Color,
    strengths: List<String>?,
    weaknesses: List<String>?,
    immunity: List<String>?
) {
    flavorText?.let {
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = flavorText,
            style = PokfinderTheme.typography.description,
            color = MaterialTheme.colors.primaryVariantText,
            modifier = Modifier.fillMaxWidth()
        )
    }

    if (pokedexData.isNotEmpty()) {
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.pokedex_data),
            style = PokfinderTheme.typography.filterTitle,
            color = typeColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        pokedexData.forEach {
            AboutItem(data = it)
        }
    }

    strengths?.let {
        AboutTypeItem(title = R.string.strength, typesList = strengths)
    }

    weaknesses?.let {
        AboutTypeItem(title = R.string.weakeness, typesList = weaknesses)
    }

    immunity?.let {
        AboutTypeItem(title = R.string.immunity, typesList = immunity)
    }

    if (training.isNotEmpty()) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.training),
            style = PokfinderTheme.typography.filterTitle,
            color = typeColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        training.forEach {
            AboutItem(data = it)
        }
    }

    if (breeding.isNotEmpty()) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.breeding),
            style = PokfinderTheme.typography.filterTitle,
            color = typeColor
        )

        Spacer(modifier = Modifier.height(20.dp))

        breeding.forEach {
            AboutItem(data = it)
        }
    }
}
