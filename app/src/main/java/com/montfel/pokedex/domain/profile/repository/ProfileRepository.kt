package com.montfel.pokedex.domain.profile.repository

import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.helper.ApiResponse

interface ProfileRepository {
    suspend fun getPokemon(pokemonName: String): ApiResponse<Pokemon>
}
