package com.montfel.pokedex.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.home.BottomSheetFilter
import com.montfel.pokedex.presentation.theme.topBarIcon

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
                    contentDescription = null,
                    tint = MaterialTheme.colors.topBarIcon
                )
            }
            IconButton(onClick = { onClick(BottomSheetFilter.Sort) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = null,
                    tint = MaterialTheme.colors.topBarIcon
                )
            }
            IconButton(onClick = { onClick(BottomSheetFilter.Filter) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null,
                    tint = MaterialTheme.colors.topBarIcon
                )
            }
        }
    }
}
