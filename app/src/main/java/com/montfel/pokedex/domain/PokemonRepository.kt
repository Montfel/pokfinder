package com.montfel.pokedex.domain

interface PokemonRepository {
    suspend fun getPokemon(pokemonName: String): Pokemon
    suspend fun getAllPokemons(): List<Result>
}
