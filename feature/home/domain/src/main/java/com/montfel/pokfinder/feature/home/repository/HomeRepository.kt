package com.montfel.pokfinder.feature.home.repository

import androidx.paging.PagingData
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemons(): Flow<PagingData<PokemonHome>>
    fun searchPokemons(queryName: String = "", queryId: Int = 0): Flow<PagingData<PokemonHome>>
    fun filterPokemonsByTypes(types: List<String>): Flow<PagingData<PokemonHome>>
    suspend fun getTypes(): ResultType<List<Type>>
    suspend fun getGenerations(): ResultType<List<Generation>>
}
