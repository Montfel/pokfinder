package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.montfel.pokedex.R
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.presentation.components.TypeCard
import com.montfel.pokedex.presentation.theme.Gray17
import com.montfel.pokedex.presentation.theme.LocalAssetHelper
import com.montfel.pokedex.presentation.theme.pokemonName
import com.montfel.pokedex.presentation.theme.pokemonNumber

@Composable
fun PokemonCard(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    val assetHelper = LocalAssetHelper.current
    val mainType = pokemon.types.firstOrNull { type -> type.slot == 1 }
    val assetBackground = assetHelper.getAsset(mainType?.type?.name ?: "")
    Box(modifier = Modifier.height(140.dp)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = assetBackground.backgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .align(Alignment.BottomCenter)
                .clickable(onClick = onClick)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "#${pokemon.id}",
                    style = MaterialTheme.typography.pokemonNumber,
                    color = Gray17,
                    modifier = Modifier.alpha(0.6f)
                )
                Text(
                    text = pokemon.name ?: "",
                    style = MaterialTheme.typography.pokemonName,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    pokemon.types.forEach {
                        TypeCard(typeName = it.type.name)
                    }
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(145.dp)
                .offset(x = 16.dp, y = 16.dp)
                .alpha(0.3f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_6x3),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = (-37).dp, y = (-18).dp)
                .width(74.dp)
                .height(32.dp)
                .alpha(0.3f)
        )
        AsyncImage(
            model = pokemon.image,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 10.dp, bottom = 10.dp)
                .size(130.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PokemonCardPreview() {
    PokemonCard(
        pokemon = Pokemon(
            id = 1,
            name = "Bulbasaur",
            image = "",
            types = emptyList()
        )
    ) {}
}
