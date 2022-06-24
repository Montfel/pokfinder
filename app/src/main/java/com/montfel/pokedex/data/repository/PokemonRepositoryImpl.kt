package com.montfel.pokedex.data.repository

import com.montfel.pokedex.data.datasource.PokemonDataSource
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.model.PokemonList
import com.montfel.pokedex.domain.repository.PokemonRepository
import com.montfel.pokedex.helper.ApiResponse
import com.montfel.pokedex.helper.requestWrapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataSource: PokemonDataSource
) : PokemonRepository {
    override suspend fun getPokemon(pokemonName: String): ApiResponse<Pokemon> {
        return requestWrapper { pokemonDataSource.getPokemon(pokemonName).toDomain() }
    }

    override suspend fun getPokemonList(limit: Int, offset: Int): ApiResponse<PokemonList> {
        return requestWrapper { pokemonDataSource.getPokemonList(limit, offset).toDomain() }
    }
}
