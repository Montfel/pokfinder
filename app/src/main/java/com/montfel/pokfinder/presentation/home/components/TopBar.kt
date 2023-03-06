package com.montfel.pokfinder.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.R
import com.montfel.pokfinder.presentation.home.bottomsheet.BottomSheetFilter
import com.montfel.pokfinder.presentation.theme.topBarIcon

@Composable
fun TopBar(onClick: (BottomSheetFilter) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            IconButton(onClick = { onClick(BottomSheetFilter.Generation) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_generation),
                    contentDescription = stringResource(id = R.string.generation_filter),
                    tint = MaterialTheme.colors.topBarIcon
                )
            }
            IconButton(onClick = { onClick(BottomSheetFilter.Sort) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = stringResource(id = R.string.sort_filter),
                    tint = MaterialTheme.colors.topBarIcon
                )
            }
            IconButton(onClick = { onClick(BottomSheetFilter.Filter) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(id = R.string.other_filters),
                    tint = MaterialTheme.colors.topBarIcon
                )
            }
        }
    }
}
