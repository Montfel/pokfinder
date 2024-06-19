package com.montfel.pokfinder.data.profile.service

import com.montfel.pokfinder.data.profile.dto.PokemonDamageRelationsDto
import com.montfel.pokfinder.data.profile.dto.PokemonProfileDto
import com.montfel.pokfinder.data.profile.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileService {
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
