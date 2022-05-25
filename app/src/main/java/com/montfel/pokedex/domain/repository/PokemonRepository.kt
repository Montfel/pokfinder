package com.montfel.pokedex.domain.repository

import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.model.Result

interface PokemonRepository {
    suspend fun getPokemon(pokemonName: String): Pokemon
    suspend fun getPokemonProfile(pokemonName: String): Pokemon
    suspend fun getAllPokemons(): List<Result>
}
