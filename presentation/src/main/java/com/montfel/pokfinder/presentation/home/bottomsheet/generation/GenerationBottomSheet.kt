package com.montfel.pokfinder.presentation.home.bottomsheet.generation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.presentation.home.bottomsheet.components.BottomSheetHeader
import com.montfel.pokfinder.presentation.home.bottomsheet.components.GenerationButton

@Composable
fun GenerationBottomSheet(
    generationList: List<Generation>,
    generationSelected: Int,
    onGenerationSelected: (Generation) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp)
    ) {
        BottomSheetHeader(
            title = R.string.generations,
            description = R.string.generations_description
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 32.dp, bottom = 50.dp)
        ) {
            items(generationList) {
                GenerationButton(
                    title = it.name,
                    firstPokemons = it.pokemonIds.take(3),
                    isEnabled = generationSelected == it.id,
                    onClick = { onGenerationSelected(it) }
                )
            }
        }
    }
}
