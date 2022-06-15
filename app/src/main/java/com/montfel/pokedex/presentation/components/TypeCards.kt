package com.montfel.pokedex.presentation.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.presentation.theme.LocalAssetHelper
import com.montfel.pokedex.presentation.theme.pokemonType

@Composable
fun TypeCards(pokemon: Pokemon) {
    val assetHelper = LocalAssetHelper.current

    LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        items(pokemon.types) { type ->
            val assetType = assetHelper.getAsset(type.type.name)
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
                        text = type.type.name,
                        style = MaterialTheme.typography.pokemonType,
                        color = Color.White,
                    )
                }
            }
        }
    }
}
