package com.montfel.pokedex.domain.home.repository

import com.montfel.pokedex.domain.home.model.Generation
import com.montfel.pokedex.domain.home.model.PokemonHome
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.helper.ApiResponse

interface HomeRepository {
    suspend fun getPokemonList(): ApiResponse<List<PokemonHome>>
    suspend fun getTypeList(): ApiResponse<List<Type>>
    suspend fun getGenerationList(): ApiResponse<List<Generation>>
}
