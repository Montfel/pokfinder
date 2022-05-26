package com.montfel.pokedex.data.repository

import com.montfel.pokedex.data.datasource.PokemonDataSource
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.model.Result
import com.montfel.pokedex.domain.repository.PokemonRepository
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataSource: PokemonDataSource
) : PokemonRepository {
    override suspend fun getPokemon(pokemonName: String): Pokemon {
        return try {
            val response = Response.success(pokemonDataSource.getPokemon(pokemonName))
            response.body()?.toDomain() ?: Pokemon()
        } catch (e: Exception) {
            Pokemon()
        }

    }

    override suspend fun getPokemonProfile(pokemonName: String): Pokemon {
        return try {
            val response = Response.success(pokemonDataSource.getPokemonProfile(pokemonName))
            response.body()?.toDomain() ?: Pokemon()
        } catch (e: Exception) {
            Pokemon()
        }
    }

    override suspend fun getPokemonSpeciesProfile(pokemonName: String): Pokemon {
        return try {
            val response = Response.success(pokemonDataSource.getPokemonSpeciesProfile(pokemonName))
            response.body()?.toDomain() ?: Pokemon()
        } catch (e: Exception) {
            Pokemon()
        }
    }

    override suspend fun getAllPokemons(): List<Result> {
        return pokemonDataSource.getAllPokemons().results.map {
            it.toDomain()
        }
    }
}
