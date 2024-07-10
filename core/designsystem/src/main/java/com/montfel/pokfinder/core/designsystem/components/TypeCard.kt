package com.montfel.pokfinder.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.model.AssetFromType
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun TypeCard(typeName: String) {
    Card(
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = AssetFromType.getAsset(typeName).typeColor
        ),
        modifier = Modifier.height(24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 4.dp)
        ) {
            Image(
                painter = painterResource(id = AssetFromType.getAsset(typeName).icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(PokfinderTheme.palette.primaryIcon),
                modifier = Modifier.size(15.dp)
            )

            Text(
                text = typeName,
                style = PokfinderTheme.typography.pokemonType,
                color = PokfinderTheme.palette.secondaryText,
            )
        }
    }
}

@Preview
@Composable
private fun TypeCardPreview() {
    PokfinderTheme {
        TypeCard(typeName = "test")
    }
}
