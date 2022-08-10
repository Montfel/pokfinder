package com.montfel.pokedex.domain.home.repository

import com.montfel.pokedex.domain.home.model.Generation
import com.montfel.pokedex.domain.home.model.PokemonHome
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.helper.Response

interface HomeRepository {
    suspend fun getPokemonList(): Response<List<PokemonHome>>
    suspend fun getTypeList(): Response<List<Type>>
    suspend fun getGenerationList(): Response<List<Generation>>
}
