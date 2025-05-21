package com.montfel.pokfinder.feature.home.ui.bottomsheet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun GenerationButton(
    title: String,
    firstPokemons: List<Int>,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isEnabled) PokfinderTheme.palette.primaryInput
        else PokfinderTheme.palette.secondaryInput
    val textColor =
        if (isEnabled) PokfinderTheme.palette.secondaryText
        else PokfinderTheme.palette.primaryVariantText
    val patternColor =
        if (isEnabled) PokfinderTheme.palette.primaryPattern
        else PokfinderTheme.palette.secondaryPattern

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick)
            .aspectRatio(1.25f)
            .background(backgroundColor)
            .clipToBounds()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            colorFilter = ColorFilter.tint(patternColor),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(110.dp)
                .offset(x = 10.dp, y = 50.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_6x3),
            contentDescription = null,
            colorFilter = ColorFilter.tint(patternColor),
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(80.dp)
                .height(35.dp)
                .offset(x = 15.dp, y = 10.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Row {
                firstPokemons.forEach { pokemonId ->
                    AsyncImage(
                        model = stringResource(id = R.string.pokemon_image_url, pokemonId),
                        contentDescription = null,
                        modifier = Modifier.size(45.dp)
                    )
                }
            }

            Text(
                text = title,
                style = PokfinderTheme.typography.description,
                color = textColor
            )
        }
    }
}
