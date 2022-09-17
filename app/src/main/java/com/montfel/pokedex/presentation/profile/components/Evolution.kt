package com.montfel.pokedex.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import com.montfel.pokedex.R
import com.montfel.pokedex.domain.profile.model.EvolutionChain
import com.montfel.pokedex.presentation.theme.*

@Composable
fun Evolution(
    typeColor: Color,
    evolutionChain: List<EvolutionChain>,
    onClick: (Int) -> Unit
) {
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = stringResource(id = R.string.evolution_chart),
        style = MaterialTheme.typography.filterTitle,
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
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = stringResource(id = R.string.evolution_arrow),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariantText),
                        alpha = 0.3f,
                    )
                    evolutionChain[index + 1].evolutionDetail.forEach { nextSpecie ->
                        Text(
                            text = nextSpecie.minLevel?.let { "Level $it" } ?: nextSpecie.trigger,
                            style = MaterialTheme.typography.pokemonNumber,
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
                contentDescription = stringResource(id = R.string.pokemon_image_description, specie.name),
                modifier = Modifier
                    .size(75.dp)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = "#${specie.id}",
            style = MaterialTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryVariantText
        )
        Text(
            text = specie.name,
            style = MaterialTheme.typography.filterTitle,
            color = MaterialTheme.colors.primaryText
        )
    }
}
