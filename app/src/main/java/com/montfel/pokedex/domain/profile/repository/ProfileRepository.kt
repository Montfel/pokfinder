package com.montfel.pokedex.domain.profile.repository

import com.montfel.pokedex.domain.profile.model.PokemonDamageRelations
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.helper.Response

interface ProfileRepository {
    suspend fun getProfile(pokemonId: String): Response<PokemonProfile>
    suspend fun getSpecies(pokemonId: String): Response<PokemonSpecies>
    suspend fun getEvolutionChain(pokemonId: String): Response<PokemonEvolutionChain>
    suspend fun getDamageRelations(typeId: String): Response<PokemonDamageRelations>
}
