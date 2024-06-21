package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.data.PokemonsQuery
import com.montfel.pokfinder.data.SearchPokemonsQuery
import com.montfel.pokfinder.data.model.entity.PokemonHomeEntity
import com.montfel.pokfinder.domain.home.model.PokemonHome

fun PokemonsQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map { it.toType() },
    )
}

fun SearchPokemonsQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map { it.toType() },
    )
}

fun FilterPokemonsByTypesQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map { it.toType() },
    )
}

fun PokemonsQuery.Pokemon_v2_pokemon.toPokemonHomeEntity(): PokemonHomeEntity {
    return PokemonHomeEntity(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map { it.toTypeDto() }
    )
}

fun PokemonHomeEntity.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name,
        types = types?.map { it.toType() },
    )
}

fun PokemonHome.toPokemonHomeEntity(): PokemonHomeEntity {
    return PokemonHomeEntity(
        id = id,
        name = name,
        types = types?.map { it.toTypeDto() }
    )
}
