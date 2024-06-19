package com.montfel.pokfinder.presentation.home.bottomsheet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.montfel.pokfinder.designsystem.R
import com.montfel.pokfinder.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.designsystem.theme.primaryInput
import com.montfel.pokfinder.designsystem.theme.primaryPattern
import com.montfel.pokfinder.designsystem.theme.primaryVariantText
import com.montfel.pokfinder.designsystem.theme.secondaryInput
import com.montfel.pokfinder.designsystem.theme.secondaryPattern
import com.montfel.pokfinder.designsystem.theme.secondaryText

@Composable
fun GenerationButton(
    title: String,
    firstPokemons: List<Int>,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isEnabled) MaterialTheme.colors.primaryInput
        else MaterialTheme.colors.secondaryInput
    val textColor =
        if (isEnabled) MaterialTheme.colors.secondaryText
        else MaterialTheme.colors.primaryVariantText
    val patternColor =
        if (isEnabled) MaterialTheme.colors.primaryPattern
        else MaterialTheme.colors.secondaryPattern

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
