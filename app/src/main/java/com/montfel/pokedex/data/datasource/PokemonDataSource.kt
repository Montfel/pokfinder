package com.montfel.pokedex.data.datasource

import com.montfel.pokedex.data.dto.AllPokemonsDto
import com.montfel.pokedex.data.dto.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDataSource {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(
        @Path(value = "pokemonName") pokemonName: String
    ): PokemonDto

    @GET("pokemon?limit=151")
    suspend fun getAllPokemons(): AllPokemonsDto
}
