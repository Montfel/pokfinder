package com.montfel.pokfinder.feature.profile.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.model.AssetFromType
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun TypeEffectivenessItem(typeName: String) {
    val assetFromType = AssetFromType.getAsset(typeName)
    Box(
        modifier = Modifier
            .size(25.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(assetFromType.typeColor)
    ) {
        Image(
            painter = painterResource(id = assetFromType.icon),
            contentDescription = stringResource(id = R.string.type, typeName),
            colorFilter = ColorFilter.tint(PokfinderTheme.palette.primaryIcon),
            modifier = Modifier
                .size(15.dp)
                .align(Alignment.Center)
        )
    }
}
