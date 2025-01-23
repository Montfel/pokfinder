package com.montfel.pokfinder.feature.home.domain.usecase

import androidx.paging.PagingData
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import com.montfel.pokfinder.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetPokemonsUseCaseImpl @Inject constructor(
    private val repository: HomeRepository
) : GetPokemonsUseCase {
    override fun invoke(): Flow<PagingData<PokemonHome>> = repository.getPokemons()
}
