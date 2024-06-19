package com.montfel.pokfinder.domain.home.repository

import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.util.ResultType

interface HomeRepository {
    suspend fun getPokemonList(): ResultType<List<PokemonHome>>
    suspend fun getTypeList(): ResultType<List<Type>>
    suspend fun getGenerationList(): ResultType<List<Generation>>
}
