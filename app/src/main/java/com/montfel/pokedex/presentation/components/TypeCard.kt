package com.montfel.pokedex.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.montfel.pokedex.presentation.theme.LocalAssetHelper
import com.montfel.pokedex.presentation.theme.pokemonType

@Composable
fun TypeCard(typeName: String) {
    val assetHelper = LocalAssetHelper.current
    val assetType = assetHelper.getAsset(typeName)

    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = assetType.typeColor,
        modifier = Modifier.height(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Image(
                painter = painterResource(id = assetType.icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = typeName,
                style = MaterialTheme.typography.pokemonType,
                color = Color.White,
            )
        }
    }
}
