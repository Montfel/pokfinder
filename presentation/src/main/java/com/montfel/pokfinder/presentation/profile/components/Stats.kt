package com.montfel.pokfinder.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.designsystem.R
import com.montfel.pokfinder.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.domain.profile.model.Stats
import com.montfel.pokfinder.designsystem.theme.primaryText
import com.montfel.pokfinder.designsystem.theme.primaryVariantText

@Composable
fun Stats(
    stats: List<Stats>,
    typeColor: Color,
) {
    val total = stats.sumOf { it.baseStat }

    Spacer(modifier = Modifier.height(30.dp))

    Text(
        text = stringResource(id = R.string.base_stats),
        style = PokfinderTheme.typography.filterTitle,
        color = typeColor
    )

    Spacer(modifier = Modifier.height(20.dp))

    stats.forEach {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = it.stat.name,
                style = PokfinderTheme.typography.pokemonType,
                color = MaterialTheme.colors.primaryText,
                modifier = Modifier.width(50.dp)
            )
            Text(
                text = it.baseStat.toString(),
                style = PokfinderTheme.typography.description,
                color = MaterialTheme.colors.primaryVariantText,
                textAlign = TextAlign.End,
                modifier = Modifier.width(36.dp)
            )
            LinearProgressIndicator(
                progress = it.baseStat.toFloat() / ((it.min + it.max) / 2).toFloat(),
                color = typeColor,
                backgroundColor = Color.Transparent,
                modifier = Modifier
                    .width(150.dp)
                    .clip(RoundedCornerShape(2.dp))
            )
            Text(
                text = it.min.toString(),
                style = PokfinderTheme.typography.description,
                color = MaterialTheme.colors.primaryVariantText,
                textAlign = TextAlign.End,
                modifier = Modifier.width(36.dp)
            )
            Text(
                text = it.max.toString(),
                style = PokfinderTheme.typography.description,
                color = MaterialTheme.colors.primaryVariantText,
                textAlign = TextAlign.End,
                modifier = Modifier.width(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.total),
            style = PokfinderTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryText,
            modifier = Modifier.width(50.dp)
        )
        Text(
            text = total.toString(),
            style = PokfinderTheme.typography.filterTitle,
            color = MaterialTheme.colors.primaryVariantText,
            textAlign = TextAlign.End,
            modifier = Modifier.width(36.dp)
        )
        Box(modifier = Modifier.width(160.dp))
        Text(
            text = stringResource(id = R.string.min),
            style = PokfinderTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryText,
            textAlign = TextAlign.End,
            modifier = Modifier.width(35.dp)
        )
        Text(
            text = stringResource(id = R.string.max),
            style = PokfinderTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryText,
            textAlign = TextAlign.End,
            modifier = Modifier.width(36.dp)
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.stats_description),
        style = PokfinderTheme.typography.pokemonType,
        color = MaterialTheme.colors.primaryVariantText
    )

    Spacer(modifier = Modifier.height(20.dp))

//    Text(
//        text = stringResource(id = R.string.type_defenses),
//        style = MaterialTheme.typography.filterTitle,
//        color = typeColor
//    )
//
//    Spacer(modifier = Modifier.height(20.dp))
//
//    Text(
//        text = stringResource(id = R.string.effectiveness).format(pokemonName),
//        style = MaterialTheme.typography.description,
//        color = MaterialTheme.colors.primaryVariantText
//    )
//
//    Spacer(modifier = Modifier.height(20.dp))
//
//    Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        repeat(9) {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(10.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                TypeEffectivenessItem(
//                    typeColor = TypeFire,
//                    image = R.drawable.ic_fire
//                )
//                Text(
//                    text = "1",
//                    style = MaterialTheme.typography.description,
//                    color = MaterialTheme.colors.primaryVariantText
//                )
//            }
//        }
//    }
//
//    Spacer(modifier = Modifier.height(20.dp))
//
//    Row(
//        horizontalArrangement = Arrangement.SpaceBetween,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        repeat(9) {
//            Column(
//                verticalArrangement = Arrangement.spacedBy(10.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                TypeEffectivenessItem(
//                    typeColor = TypeFire,
//                    image = R.drawable.ic_fire
//                )
//                Text(
//                    text = "1",
//                    style = MaterialTheme.typography.description,
//                    color = MaterialTheme.colors.primaryVariantText
//                )
//            }
//        }
//    }
}
