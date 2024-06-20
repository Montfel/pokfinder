package com.montfel.pokfinder.data.dto.home

import com.montfel.pokfinder.data.PokemonsQuery
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.profile.model.Type
import com.montfel.pokfinder.domain.profile.model.Types

fun PokemonsQuery.Pokemon_v2_pokemon.toDomain(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map { (slot, pokemonType) ->
            Types(
                slot = slot,
                type = Type(
                    name = pokemonType?.name?.replaceFirstChar { it.uppercase() }.orEmpty()
                )
            )
        }
    )
}
