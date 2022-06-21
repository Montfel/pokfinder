package com.montfel.pokedex.presentation.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.BottomSheetButton
import com.montfel.pokedex.presentation.components.BottomSheetHeader

@Composable
fun GenerationBottomSheet() {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 30.dp)
    ) {
        BottomSheetHeader(
            title = R.string.generations,
            description = R.string.generations_description
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                BottomSheetButton(title = R.string.generations)
            }
            item {
                BottomSheetButton(title = R.string.generations)
            }
            item {
                BottomSheetButton(title = R.string.generations)
            }
            item {
                BottomSheetButton(title = R.string.generations)
            }
            item {
                BottomSheetButton(title = R.string.generations)
            }
        }
    }
}
