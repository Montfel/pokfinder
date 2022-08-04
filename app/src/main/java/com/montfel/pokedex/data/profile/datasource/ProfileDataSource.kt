package com.montfel.pokedex.data.profile.datasource

import com.montfel.pokedex.data.dto.rest.PokemonRestDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileDataSource {
    @GET("pokemon/{pokemonName}")
    suspend fun getPokemon(
        @Path(value = "pokemonName") pokemonName: String
    ): PokemonRestDto
}
