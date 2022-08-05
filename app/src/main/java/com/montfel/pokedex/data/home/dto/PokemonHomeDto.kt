package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.PokemonListQuery
import com.montfel.pokedex.data.dto.TypeDto
import com.montfel.pokedex.domain.model.PokemonHome
import com.montfel.pokedex.domain.model.Types

fun PokemonListQuery.Pokemon_v2_pokemon.toDomain() = PokemonHome(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    types = pokemon_v2_pokemontypes.map { type ->
        Types(
            slot = type.slot,
            type = TypeDto(name = type.pokemon_v2_type?.name).toDomain()
        )
    }
)
