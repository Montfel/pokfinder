package com.montfel.pokfinder.feature.home.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.feature.home.ui.bottomsheet.BottomSheetType

@Composable
fun TopBar(onClick: (BottomSheetType) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(onClick = { onClick(BottomSheetType.Generation) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_generation),
                    contentDescription = stringResource(id = R.string.generation_filter),
                    tint = PokfinderTheme.palette.topBarIcon
                )
            }

            IconButton(onClick = { onClick(BottomSheetType.Sort) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = stringResource(id = R.string.sort_filter),
                    tint = PokfinderTheme.palette.topBarIcon
                )
            }

            IconButton(onClick = { onClick(BottomSheetType.Filter) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.other_filters),
                    tint = PokfinderTheme.palette.topBarIcon
                )
            }
        }
    }
}
