package com.montfel.pokedex.domain.repository

import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.model.PokemonList
import com.montfel.pokedex.helper.ApiResponse

interface PokemonRepository {
    suspend fun getPokemon(pokemonName: String): ApiResponse<Pokemon>
    suspend fun getPokemonList(limit: Int, offset: Int): ApiResponse<PokemonList>
}
