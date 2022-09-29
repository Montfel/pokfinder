package com.montfel.pokfinder.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.domain.profile.model.AboutData
import com.montfel.pokfinder.presentation.theme.description
import com.montfel.pokfinder.presentation.theme.pokemonType
import com.montfel.pokfinder.presentation.theme.primaryText
import com.montfel.pokfinder.presentation.theme.primaryVariantText

@Composable
fun AboutItem(data: AboutData) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = data.title),
            style = MaterialTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryText,
            modifier = Modifier.width(100.dp)
        )
        Text(
            text = data.description,
            style = MaterialTheme.typography.description,
            color = MaterialTheme.colors.primaryVariantText
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}
