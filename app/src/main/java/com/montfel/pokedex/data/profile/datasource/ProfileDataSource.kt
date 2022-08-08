package com.montfel.pokedex.data.profile.datasource

import com.montfel.pokedex.data.profile.dto.PokemonDamageRelationsDto
import com.montfel.pokedex.data.profile.dto.PokemonEvolutionChainDto
import com.montfel.pokedex.data.profile.dto.PokemonProfileDto
import com.montfel.pokedex.data.profile.dto.PokemonSpeciesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileDataSource {
    @GET("pokemon/{pokemonId}")
    suspend fun getProfile(
        @Path(value = "pokemonId") pokemonId: String
    ): PokemonProfileDto

    @GET("pokemon-species/{pokemonId}")
    suspend fun getSpecies(
        @Path(value = "pokemonId") pokemonId: String
    ): PokemonSpeciesDto

    @GET("evolution-chain/{pokemonId}")
    suspend fun getEvolutionChain(
        @Path(value = "pokemonId") pokemonId: String
    ): PokemonEvolutionChainDto

    @GET("type/{typeId}")
    suspend fun getDamageRelations(
        @Path(value = "typeId") typeId: String
    ): PokemonDamageRelationsDto
}
