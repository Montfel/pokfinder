package com.montfel.pokfinder.domain.home.usecase

import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.model.SortOptions

class SortPokemonsUseCase {

    operator fun invoke(
        sortOption: SortOptions = SortOptions.SmallestNumber,
        pokemons: List<PokemonHome>
    ): List<PokemonHome> {
        return when (sortOption) {
            SortOptions.SmallestNumber -> pokemons.sortedBy { it.id }
            SortOptions.HighestNumber -> pokemons.sortedByDescending { it.id }
            SortOptions.Alphabetical -> pokemons.sortedBy { it.name }
            SortOptions.ReverseAlphabetical -> pokemons.sortedByDescending { it.name }
        }
    }
}
