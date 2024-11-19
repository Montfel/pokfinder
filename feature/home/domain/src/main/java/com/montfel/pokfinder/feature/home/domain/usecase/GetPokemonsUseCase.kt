package com.montfel.pokfinder.feature.home.domain.usecase

import androidx.paging.PagingData
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import kotlinx.coroutines.flow.Flow

interface GetPokemonsUseCase {
    operator fun invoke(): Flow<PagingData<PokemonHome>>
}