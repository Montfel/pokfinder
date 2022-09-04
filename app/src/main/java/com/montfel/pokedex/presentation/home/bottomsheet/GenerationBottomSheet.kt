package com.montfel.pokedex.presentation.home.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.domain.home.model.Generation
import com.montfel.pokedex.presentation.home.bottomsheet.components.BottomSheetHeader
import com.montfel.pokedex.presentation.home.bottomsheet.components.GenerationButton

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
            contentPadding = PaddingValues(vertical = 32.dp)
        ) {
            items(generationList) {
                GenerationButton(
                    title = it.name,
                    firstPokemons = it.pokemonId.take(3),
                    isEnabled = generationSelected == it.id,
                    onClick = { onGenerationSelected(it) }
                )
            }
        }
    }
}
