package com.montfel.pokedex.data

import com.montfel.pokedex.domain.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDataSource {
    @GET("pokemon/{pokemonName}")
    fun getPokemon(
        @Path(value = "pokemonName") pokemonName: String
    ): Call<Pokemon>
}
