package com.montfel.pokedex.data.datasource

import com.montfel.pokedex.data.dto.rest.PokemonListDto
import com.montfel.pokedex.data.dto.rest.PokemonRestDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonDataSource {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(
        @Path(value = "pokemonName") pokemonName: String
    ): PokemonRestDto

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query(value = "limit") limit: Int,
        @Query(value = "offset") offset: Int
    ): PokemonListDto

    @GET("pokemon?limit=1")
    suspend fun getPokemonCount(): PokemonListDto
}
