package com.montfel.pokedex.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.description
import com.montfel.pokedex.presentation.theme.primaryInput
import com.montfel.pokedex.presentation.theme.secondaryText

@Composable
fun RetryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryInput,
            contentColor = MaterialTheme.colors.secondaryText
        ),
        modifier = modifier
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.retry),
                style = MaterialTheme.typography.description,
            )
        }
    }
}
