package com.montfel.pokedex.domain.repository

import com.montfel.pokedex.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemon(pokemonName: String): Pokemon
    suspend fun getPokemonProfile(pokemonName: String): Pokemon
    suspend fun getPokemonSpeciesProfile(pokemonName: String): Pokemon
    suspend fun getAllPokemons(): List<Pokemon>
}
