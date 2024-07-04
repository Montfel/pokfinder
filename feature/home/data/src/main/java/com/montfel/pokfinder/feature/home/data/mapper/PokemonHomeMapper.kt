package com.montfel.pokfinder.feature.home.data.mapper

import com.montfel.core.database.model.entity.PokemonHomeEntity
import com.montfel.pokfinder.core.network.FilterPokemonsByGenerationQuery
import com.montfel.pokfinder.core.network.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.core.network.PokemonsQuery
import com.montfel.pokfinder.core.network.SearchPokemonsQuery
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome

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

fun FilterPokemonsByGenerationQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
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
