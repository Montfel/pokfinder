package com.montfel.pokfinder.presentation.profile.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.R
import com.montfel.pokfinder.presentation.theme.description
import com.montfel.pokfinder.presentation.theme.pokemonType
import com.montfel.pokfinder.presentation.theme.primaryText
import com.montfel.pokfinder.presentation.theme.primaryVariantText

@Composable
fun AboutTypeItem(@StringRes title: Int, typesList: List<String>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.pokemonType,
            color = MaterialTheme.colors.primaryText,
            modifier = Modifier.width(100.dp)
        )
        if (typesList.isEmpty()) {
            Text(
                text = stringResource(id = R.string.none),
                style = MaterialTheme.typography.description,
                color = MaterialTheme.colors.primaryVariantText
            )
        } else {
            typesList.forEach {
                TypeEffectivenessItem(typeName = it)
            }
        }
    }
}
