package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.data.dto.StatsDto
import com.montfel.pokedex.presentation.components.TypeEffectivenessItem
import com.montfel.pokedex.presentation.theme.*

data class Stats(
    val statName: String?,
    val stat: Int,
//    val statMin: Int,
//    val statMax: Int
)

@Composable
fun Stats(
    stats: List<StatsDto>,
    typeColor: Color,
    pokemonName: String
) {
    val statsList = stats.map {
        Stats(
            statName = it.name,
            stat = it.baseStat
        )
    }
    val total = stats.sumOf { it.baseStat }

    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = stringResource(id = R.string.base_stats),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    statsList.forEach {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = it.statName ?: "",
                style = MaterialTheme.typography.pokemonType,
                color = Gray17,
                modifier = Modifier.width(50.dp)
            )
            Text(
                text = it.stat.toString(),
                style = MaterialTheme.typography.description,
                color = Gray74,
                textAlign = TextAlign.End,
                modifier = Modifier.width(35.dp)
            )
            LinearProgressIndicator(
                progress = it.stat.toFloat() / 200,
                color = typeColor,
                backgroundColor = Color.Transparent,
                modifier = Modifier
                    .width(150.dp)
                    .clip(RoundedCornerShape(2.dp))
            )
            Text(
                text = "200",
                style = MaterialTheme.typography.description,
                color = Gray74,
                textAlign = TextAlign.End,
                modifier = Modifier.width(35.dp)
            )
            Text(
                text = "294",
                style = MaterialTheme.typography.description,
                color = Gray74,
                textAlign = TextAlign.End,
                modifier = Modifier.width(35.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.total),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            modifier = Modifier.width(50.dp)
        )
        Text(
            text = total.toString(),
            style = MaterialTheme.typography.filterTitle,
            color = Gray74,
            textAlign = TextAlign.End,
            modifier = Modifier.width(35.dp)
        )
        Box(modifier = Modifier.width(160.dp))
        Text(
            text = stringResource(id = R.string.min),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            textAlign = TextAlign.End,
            modifier = Modifier.width(35.dp)
        )
        Text(
            text = stringResource(id = R.string.max),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            textAlign = TextAlign.End,
            modifier = Modifier.width(35.dp)
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.stats_description),
        style = MaterialTheme.typography.pokemonType,
        color = Gray74
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.type_defenses),
        style = MaterialTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.effectiveness).format(pokemonName),
        style = MaterialTheme.typography.description,
        color = Gray74
    )

    Spacer(modifier = Modifier.height(20.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(9) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TypeEffectivenessItem(
                    typeColor = TypeFire,
                    image = R.drawable.ic_fire
                )
                Text(
                    text = "1",
                    style = MaterialTheme.typography.description,
                    color = Gray74
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        repeat(9) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TypeEffectivenessItem(
                    typeColor = TypeFire,
                    image = R.drawable.ic_fire
                )
                Text(
                    text = "1",
                    style = MaterialTheme.typography.description,
                    color = Gray74
                )
            }
        }
    }
}
