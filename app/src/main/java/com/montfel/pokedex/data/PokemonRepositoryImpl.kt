package com.montfel.pokedex.data

import com.montfel.pokedex.domain.AllPokemons
import com.montfel.pokedex.domain.Pokemon
import com.montfel.pokedex.domain.PokemonRepository
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataSource: PokemonDataSource
) : PokemonRepository {
    override suspend fun getPokemon(pokemonName: String): Pokemon {
        return try {
            val response = Response.success(pokemonDataSource.getPokemon(pokemonName))
            response.body()?.toDomain() ?: Pokemon(height = 0, name = "bla", id = -1)
        } catch (e: Exception) {
            Pokemon(height = 0, name = "bla", id = -1)
        }

    }

    override suspend fun getAllPokemons(): AllPokemons {
        return pokemonDataSource.getAllPokemons().toDomain()
    }
}
