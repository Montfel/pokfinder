package com.montfel.pokfinder.domain.home.repository

import androidx.paging.PagingData
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.util.ResultType
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemons(): Flow<PagingData<PokemonHome>>
    fun searchPokemons(queryName: String = "", queryId: Int = 0): Flow<PagingData<PokemonHome>>
    fun filterPokemonsByTypes(types: List<String>): Flow<PagingData<PokemonHome>>
    suspend fun getTypes(): ResultType<List<Type>>
    suspend fun getGenerations(): ResultType<List<Generation>>
}
