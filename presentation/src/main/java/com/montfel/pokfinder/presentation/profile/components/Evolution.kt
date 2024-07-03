package com.montfel.pokfinder.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.core.designsystem.theme.pokeballIcon
import com.montfel.pokfinder.core.designsystem.theme.primaryText
import com.montfel.pokfinder.core.designsystem.theme.primaryVariantText
import com.montfel.pokfinder.domain.profile.model.EvolutionChain

@Composable
fun Evolution(
    typeColor: Color,
    evolutionChain: List<EvolutionChain>,
    onClick: (Int) -> Unit
) {
    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = stringResource(id = R.string.evolution_chart),
        style = PokfinderTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(30.dp))

    evolutionChain.forEachIndexed { index, specie ->
        if (index < evolutionChain.size - 1) {
            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                PokemonEvolution(
                    specie = specie,
                    onClick = onClick
                )

                Column(horizontalAlignment = CenterHorizontally) {
                    Image(
                        imageVector = Icons.AutoMirrored.Default.ArrowForward,
                        contentDescription = stringResource(id = R.string.evolution_arrow),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariantText),
                        alpha = 0.3f,
                    )

                    evolutionChain[index + 1].evolutionDetail.forEach { (minLevel, trigger) ->
                        Text(
                            text = minLevel?.let { "Level $it" } ?: trigger,
                            style = PokfinderTheme.typography.pokemonNumber,
                            color = MaterialTheme.colors.primaryText
                        )
                    }
                }

                PokemonEvolution(
                    specie = evolutionChain[index + 1],
                    onClick = onClick
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun PokemonEvolution(
    specie: EvolutionChain,
    onClick: (Int) -> Unit
) {
    Column(horizontalAlignment = CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .clickable { onClick(specie.id) }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.pokeballIcon)
            )

            AsyncImage(
                model = stringResource(id = R.string.pokemon_image_url, specie.id),
                contentDescription = stringResource(
                    id = R.string.pokemon_image_description,
                    specie.name
                ),
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.Center)
            )
        }

        Text(
            text = "#${specie.id}",
            style = PokfinderTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryVariantText
        )

        Text(
            text = specie.name,
            style = PokfinderTheme.typography.filterTitle,
            color = MaterialTheme.colors.primaryText
        )
    }
}
