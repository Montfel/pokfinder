package com.montfel.pokedex.domain.home.repository

import com.montfel.pokedex.domain.home.model.Generation
import com.montfel.pokedex.domain.home.model.PokemonHome
import com.montfel.pokedex.domain.home.model.TypeHome
import com.montfel.pokedex.helper.ApiResponse

interface HomeRepository {
    suspend fun getPokemonList(): ApiResponse<List<PokemonHome>>
    suspend fun getTypeList(): ApiResponse<List<TypeHome>>
    suspend fun getGenerationList(): ApiResponse<List<Generation>>
}
