package com.montfel.pokfinder.feature.home.data.mapper

import com.montfel.pokfinder.core.database.model.dto.TypeDto
import com.montfel.pokfinder.core.database.model.entity.PokemonHomeEntity
import com.montfel.pokfinder.core.network.FilterPokemonsByGenerationQuery
import com.montfel.pokfinder.core.network.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.core.network.PokemonsQuery
import com.montfel.pokfinder.core.network.SearchPokemonsQuery
import com.montfel.pokfinder.core.network.SortPokemonsByIdQuery
import com.montfel.pokfinder.core.network.SortPokemonsByNameQuery
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome

fun SearchPokemonsQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map(SearchPokemonsQuery.Pokemon_v2_pokemontype::toType),
    )
}

fun FilterPokemonsByTypesQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map(FilterPokemonsByTypesQuery.Pokemon_v2_pokemontype::toType),
    )
}

fun FilterPokemonsByGenerationQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map(FilterPokemonsByGenerationQuery.Pokemon_v2_pokemontype::toType),
    )
}

fun SortPokemonsByNameQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map(SortPokemonsByNameQuery.Pokemon_v2_pokemontype::toType),
    )
}

fun SortPokemonsByIdQuery.Pokemon_v2_pokemon.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map(SortPokemonsByIdQuery.Pokemon_v2_pokemontype::toType),
    )
}

fun PokemonsQuery.Pokemon_v2_pokemon.toPokemonHomeEntity(): PokemonHomeEntity {
    return PokemonHomeEntity(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = pokemon_v2_pokemontypes.map(PokemonsQuery.Pokemon_v2_pokemontype::toTypeDto),
    )
}

fun PokemonHomeEntity.toPokemonHome(): PokemonHome {
    return PokemonHome(
        id = id,
        name = name,
        types = types?.map(TypeDto::toType),
    )
}
