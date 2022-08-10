package com.montfel.pokedex.data.home.dto

import com.montfel.pokedex.PokemonListQuery
import com.montfel.pokedex.domain.home.model.PokemonHome
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.domain.profile.model.Types
import com.montfel.pokedex.helper.AssetFromType

fun PokemonListQuery.Pokemon_v2_pokemon.toDomain() = PokemonHome(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    types = pokemon_v2_pokemontypes.map { type ->
        Types(
            slot = type.slot,
            type = Type(
                name = type.pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() } ?: "",
                assetFromType = AssetFromType.getAsset(type.pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
                    ?: "")
            )
        )
    }
)
