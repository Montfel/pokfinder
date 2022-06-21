package com.montfel.pokedex.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun TypeEffectivenessItem(
    typeColor: Color,
    @DrawableRes image: Int
) {
    Box(
        modifier = Modifier
            .size(25.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(typeColor)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.Center)
        )
    }
}
