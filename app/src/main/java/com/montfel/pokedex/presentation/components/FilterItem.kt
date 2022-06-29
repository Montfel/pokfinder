package com.montfel.pokedex.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FilterItem(
    @DrawableRes icon: Int,
    typeColor: Color,
) {
    var isEnabled by remember { mutableStateOf(false) } //TODO: move state to parent
    val color = if (isEnabled) Color.White else typeColor ///TODO: change to MaterialTheme.colors
    Box(
        contentAlignment = Alignment.Center,
        modifier =
        if (isEnabled) Modifier
            .size(50.dp)
            .background(typeColor, CircleShape)
        else Modifier.size(50.dp)
    ) {
        IconButton(onClick = { isEnabled = !isEnabled }) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color),
                modifier = Modifier.size(25.dp)
            )
        }
    }
}
