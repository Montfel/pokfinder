package com.montfel.pokfinder.feature.home.ui.bottomsheet.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.designsystem.model.AssetFromType
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun FilterSection(
    @StringRes title: Int,
    items: List<Type>,
    itemsSelected: List<Type>,
    onFilterSelected: (Type) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = title),
            style = PokfinderTheme.typography.filterTitle,
            color = PokfinderTheme.palette.primaryText,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(items) { item ->
                val asset = AssetFromType.getAsset(item.name)

                FilterItem(
                    icon = asset.icon,
                    typeColor = asset.typeColor,
                    isEnabled = itemsSelected.contains(item),
                    onClick = { onFilterSelected(item) },
                )
            }
        }
    }
}
