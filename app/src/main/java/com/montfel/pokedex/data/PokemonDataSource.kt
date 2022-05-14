package com.montfel.pokedex.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDataSource {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(
        @Path(value = "pokemonName") pokemonName: String
    ): PokemonDto

    @GET("pokemon?limit=1126")
    suspend fun getAllPokemons(): AllPokemonsDto
}
