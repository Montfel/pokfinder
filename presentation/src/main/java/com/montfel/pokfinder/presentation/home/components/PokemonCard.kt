package com.montfel.pokfinder.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.components.TypeCard
import com.montfel.pokfinder.core.designsystem.model.AssetFromType
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.core.designsystem.theme.numberOverBackgroundColor
import com.montfel.pokfinder.core.designsystem.theme.secondaryText
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonCard(
    pokemon: PokemonHome,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .height(140.dp)
            .clipToBounds()
    ) {
        Card(
            onClick = onClick,
            shape = RoundedCornerShape(10.dp),
            backgroundColor = AssetFromType.getAsset(pokemon.types?.firstOrNull()?.name).backgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .align(Alignment.BottomCenter)
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "#${pokemon.id}",
                        style = PokfinderTheme.typography.pokemonNumber,
                        color = MaterialTheme.colors.numberOverBackgroundColor
                    )

                    Text(
                        text = pokemon.name,
                        style = PokfinderTheme.typography.pokemonName,
                        color = MaterialTheme.colors.secondaryText,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        pokemon.types?.forEach { type ->
                            type.name?.let {
                                TypeCard(typeName = it)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.size(100.dp))
            }
        }

        Image(
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            alpha = 0.3f,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(140.dp)
                .offset(x = 15.dp, y = 15.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_6x3),
            contentDescription = null,
            alpha = 0.3f,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-36).dp, y = (-18).dp)
                .width(74.dp)
                .height(32.dp)
        )

        AsyncImage(
            model = stringResource(id = R.string.pokemon_image_url, pokemon.id),
            contentDescription = stringResource(
                id = R.string.pokemon_image_description,
                pokemon.name
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp)
                .size(130.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(
        pokemon = PokemonHome(
            id = 1,
            name = "Bulbasaur",
            types = emptyList()
        ),
        onClick = {}
    )
}
