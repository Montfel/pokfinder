package com.montfel.pokedex.data.repository

import com.montfel.pokedex.data.datasource.PokemonDataSource
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.repository.PokemonRepository
import com.montfel.pokedex.domain.model.Result
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
            response.body()?.toDomain() ?: Pokemon(
                height = 0,
                name = "bla",
                id = -1,
                image = "",
                types = emptyList()
            )
        } catch (e: Exception) {
            Pokemon(height = 0, name = "bla", id = -1, image = "", types = emptyList())
        }

    }

    override suspend fun getAllPokemons(): List<Result> {
        return pokemonDataSource.getAllPokemons().results.map {
            it.toDomain()
        }
    }
}
