package com.montfel.pokfinder.data.datasource.remote.service

import com.montfel.pokfinder.data.model.dto.PokemonDamageRelationsDto
import com.montfel.pokfinder.data.model.dto.PokemonProfileDto
import com.montfel.pokfinder.data.model.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProfileService {
    @GET("pokemon/{pokemonId}")
    suspend fun getProfile(
        @Path(value = "pokemonId") pokemonId: Int
    ): PokemonProfileDto

    @GET("pokemon-species/{pokemonId}")
    suspend fun getSpecies(
        @Path(value = "pokemonId") pokemonId: Int
    ): PokemonSpeciesDto

    @GET("type/{typeId}")
    suspend fun getDamageRelations(
        @Path(value = "typeId") typeId: String
    ): PokemonDamageRelationsDto
}
