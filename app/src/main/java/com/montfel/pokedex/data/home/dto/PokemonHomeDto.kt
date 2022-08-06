package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.PokemonListQuery
import com.montfel.pokedex.domain.home.model.TypeHome
import com.montfel.pokedex.domain.home.model.TypesHome
import com.montfel.pokedex.domain.home.model.PokemonHome

fun PokemonListQuery.Pokemon_v2_pokemon.toDomain() = PokemonHome(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    types = pokemon_v2_pokemontypes.map { type ->
        TypesHome(
            slot = type.slot,
            type = TypeHome(
                name = type.pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() } ?: "",
            )
        )
    }
)
