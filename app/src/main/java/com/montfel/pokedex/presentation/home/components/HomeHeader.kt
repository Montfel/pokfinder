package com.montfel.pokedex.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.applicationTitle
import com.montfel.pokedex.presentation.theme.description
import com.montfel.pokedex.presentation.theme.primaryText
import com.montfel.pokedex.presentation.theme.primaryVariantText

@Composable
fun HomeHeader() {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.applicationTitle,
            color = MaterialTheme.colors.primaryText
        )

        Text(
            text = stringResource(id = R.string.subtitle),
            style = MaterialTheme.typography.description,
            color = MaterialTheme.colors.primaryVariantText
        )
    }
}
