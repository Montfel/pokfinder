package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.montfel.pokedex.R
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.presentation.theme.Gray17
import com.montfel.pokedex.presentation.theme.LocalAssetHelper

@Composable
fun PokemonCard (pokemon: Pokemon) {
    val assetHelper = LocalAssetHelper.current
    val type = pokemon.types.first { type -> type.slot == 1 }
    val assetBackground = assetHelper.getAsset(type.name)
    Box(modifier = Modifier.height(140.dp)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = assetBackground.backgroundColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .align(Alignment.BottomCenter)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "#${pokemon.id}",
                    style = MaterialTheme.typography.h6,
                    color = Gray17,
                    modifier = Modifier.alpha(0.6f)
                )
                Text(
                    text = pokemon.name,
                    style = MaterialTheme.typography.h3,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(5.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    items(pokemon.types) { type ->
                        val assetType = assetHelper.getAsset(type.name)
                        Card(
                            shape = RoundedCornerShape(3.dp),
                            backgroundColor = assetType.typeColor,
                            modifier = Modifier.height(25.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier.padding(horizontal = 5.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = assetType.icon),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(Color.White),
                                    modifier = Modifier.size(15.dp)
                                )
                                Text(
                                    text = type.name,
                                    style = MaterialTheme.typography.subtitle1,
                                    color = Color.White,
                                )
                            }
                        }
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
                .offset(x = 15.dp, y = 15.dp)
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
