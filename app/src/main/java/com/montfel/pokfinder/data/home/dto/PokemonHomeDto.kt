package com.montfel.pokfinder.data.home.dto

import com.montfel.pokfinder.PokemonListQuery
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.profile.model.Types
import com.montfel.pokfinder.domain.AssetFromType

fun PokemonListQuery.Pokemon_v2_pokemon.toDomain() = PokemonHome(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    types = pokemon_v2_pokemontypes.map { type ->
        Types(
            slot = type.slot,
            type = Type(
                name = type.pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() } ?: "",
                assetFromType = AssetFromType.getAsset(type.pokemon_v2_type?.name ?: "")
            )
        )
    }
)
