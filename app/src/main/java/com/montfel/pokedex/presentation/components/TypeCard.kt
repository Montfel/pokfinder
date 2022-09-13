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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.presentation.theme.pokemonType
import com.montfel.pokedex.presentation.theme.primaryIcon
import com.montfel.pokedex.presentation.theme.secondaryText

@Composable
fun TypeCard(type: Type) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = type.assetFromType.typeColor,
        modifier = Modifier.height(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            Image(
                painter = painterResource(id = type.assetFromType.icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryIcon),
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = type.name,
                style = MaterialTheme.typography.pokemonType,
                color = MaterialTheme.colors.secondaryText,
            )
        }
    }
}
