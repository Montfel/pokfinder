package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.data.profile.dto.SpeciesDto
import com.montfel.pokedex.presentation.theme.*

@Composable
fun Evolution(
    typeColor: Color,
    evolutionChain: List<SpeciesDto>
) {
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = stringResource(id = R.string.evolution_chart),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )
    Spacer(modifier = Modifier.height(30.dp))
    evolutionChain.forEachIndexed { index, specie ->
        if (index != evolutionChain.size - 1) {
            Row(
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                PokemonEvolution(specie = specie)
                Column(horizontalAlignment = CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_front),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryVariantText),
                        alpha = 0.1f,
                    )
                    Text(
                        text = "(Level ${evolutionChain[index + 1].minLevel})",
                        style = MaterialTheme.typography.pokemonNumber,
                        color = MaterialTheme.colors.primaryText
                    )
                }
                PokemonEvolution(specie = evolutionChain[index + 1])
            }
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun PokemonEvolution(specie: SpeciesDto) {
    Column(horizontalAlignment = CenterHorizontally) {
        Box(modifier = Modifier.size(100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.pokeballIcon)
            )
            Image(
                painter = painterResource(id = R.drawable.bulba),
                contentDescription = null,
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
