package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.TypeEffectivenessItem
import com.montfel.pokedex.presentation.theme.*

@Composable
fun About(
    flavorText: String,
    data: Map<Int, String>,
    training: Map<Int, String>,
    breeding: Map<Int, String>,
    typeColor: Color,
    strength: List<String>? = emptyList(),
    weakness: List<String>? = emptyList(),
) {
    val assetHelper = LocalAssetHelper.current
    Spacer(modifier = Modifier.height(40.dp))

    Text(
        text = flavorText,
        style = MaterialTheme.typography.description,
        color = Gray74,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = stringResource(id = R.string.pokedex_data),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    data.forEach {
        AboutItem(map = it)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.weakeness),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            modifier = Modifier.width(85.dp)
        )
        weakness?.forEach {
            val asset = assetHelper.getAsset(it)
            TypeEffectivenessItem(typeColor = asset.typeColor, image = asset.icon)
        }
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.strength),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            modifier = Modifier.width(85.dp)
        )
        strength?.forEach {
            val asset = assetHelper.getAsset(it)
            TypeEffectivenessItem(typeColor = asset.typeColor, image = asset.icon)
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.training),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    training.forEach {
        AboutItem(map = it)
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.breeding),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    breeding.forEach {
        AboutItem(map = it)
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.location),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    breeding.forEach {
        AboutItem(map = it)
    }
}

@Composable
fun AboutItem(map: Map.Entry<Int, String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = map.key),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            modifier = Modifier.width(85.dp)
        )
        Text(
            text = map.value,
            style = MaterialTheme.typography.description,
            color = Gray74
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
