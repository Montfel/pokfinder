package com.montfel.pokfinder.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.designsystem.R
import com.montfel.pokfinder.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.designsystem.theme.primaryInput
import com.montfel.pokfinder.designsystem.theme.secondaryText

@Composable
fun RetryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primaryInput,
                contentColor = MaterialTheme.colors.secondaryText
            ),
            modifier = modifier.align(Alignment.Center)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.retry),
                    style = PokfinderTheme.typography.description,
                )
            }
        }
    }
}
