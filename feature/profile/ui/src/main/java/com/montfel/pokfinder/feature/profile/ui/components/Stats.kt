package com.montfel.pokfinder.feature.profile.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.feature.profile.domain.model.Stat

@Composable
fun Stats(
    stats: List<Stat>,
    typeColor: Color,
) {
    val total = stats.sumOf { it.baseStat ?: 0 }

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
                text = it.name.orEmpty(),
                style = PokfinderTheme.typography.pokemonType,
                color = PokfinderTheme.palette.primaryText,
                modifier = Modifier.width(50.dp)
            )

            Text(
                text = it.baseStat.toString(),
                style = PokfinderTheme.typography.description,
                color = PokfinderTheme.palette.primaryVariantText,
                textAlign = TextAlign.End,
                modifier = Modifier.width(36.dp)
            )

            LinearProgressIndicator(
                progress = {
                    it.baseStat?.toFloat()
                        ?.div((it.min?.plus(it.max ?: 0))?.div(2) ?: 0) ?: 0f
                },
                modifier = Modifier
                    .width(150.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = typeColor,
                trackColor = Color.Transparent,
            )

            Text(
                text = it.min.toString(),
                style = PokfinderTheme.typography.description,
                color = PokfinderTheme.palette.primaryVariantText,
                textAlign = TextAlign.End,
                modifier = Modifier.width(36.dp)
            )

            Text(
                text = it.max.toString(),
                style = PokfinderTheme.typography.description,
                color = PokfinderTheme.palette.primaryVariantText,
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
            color = PokfinderTheme.palette.primaryText,
            modifier = Modifier.width(50.dp)
        )

        Text(
            text = total.toString(),
            style = PokfinderTheme.typography.filterTitle,
            color = PokfinderTheme.palette.primaryVariantText,
            textAlign = TextAlign.End,
            modifier = Modifier.width(36.dp)
        )

        Box(modifier = Modifier.width(160.dp))

        Text(
            text = stringResource(id = R.string.min),
            style = PokfinderTheme.typography.pokemonType,
            color = PokfinderTheme.palette.primaryText,
            textAlign = TextAlign.End,
            modifier = Modifier.width(35.dp)
        )

        Text(
            text = stringResource(id = R.string.max),
            style = PokfinderTheme.typography.pokemonType,
            color = PokfinderTheme.palette.primaryText,
            textAlign = TextAlign.End,
            modifier = Modifier.width(36.dp)
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(id = R.string.stats_description),
        style = PokfinderTheme.typography.pokemonType,
        color = PokfinderTheme.palette.primaryVariantText
    )

    Spacer(modifier = Modifier.height(20.dp))
}
