package com.montfel.pokfinder.domain.home.repository

import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.helper.Response

interface HomeRepository {
    suspend fun getPokemonList(): Response<List<PokemonHome>>
    suspend fun getTypeList(): Response<List<Type>>
    suspend fun getGenerationList(): Response<List<Generation>>
}
