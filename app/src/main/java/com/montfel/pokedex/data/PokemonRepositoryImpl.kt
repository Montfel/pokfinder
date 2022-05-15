package com.montfel.pokedex.data

import com.montfel.pokedex.domain.Pokemon
import com.montfel.pokedex.domain.PokemonRepository
import com.montfel.pokedex.domain.Result
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
