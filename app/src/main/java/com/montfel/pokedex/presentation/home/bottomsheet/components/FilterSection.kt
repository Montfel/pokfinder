package com.montfel.pokedex.presentation.home.bottomsheet.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.helper.Asset
import com.montfel.pokedex.presentation.theme.filterTitle
import com.montfel.pokedex.presentation.theme.primaryText

@Composable
fun FilterSection(
    @StringRes title: Int,
    items: List<Asset>,
    itemsSelected: MutableList<Int>,
    onFilterSelected: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.filterTitle,
            color = MaterialTheme.colors.primaryText,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            itemsIndexed(items) { index, item ->
                FilterItem(
                    icon = item.icon,
                    typeColor = item.typeColor,
                    isEnabled = itemsSelected.contains(index),
                    onClick = { onFilterSelected(index) },
                )
            }
        }
    }
}
