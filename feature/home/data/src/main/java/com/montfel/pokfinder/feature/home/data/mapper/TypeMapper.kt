package com.montfel.pokfinder.feature.home.data.mapper

import com.montfel.pokfinder.core.database.model.dto.TypeDto
import com.montfel.pokfinder.core.database.model.dto.TypeNameDto
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.network.FilterPokemonsByGenerationQuery
import com.montfel.pokfinder.core.network.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.core.network.PokemonsQuery
import com.montfel.pokfinder.core.network.SearchPokemonsQuery
import com.montfel.pokfinder.core.network.SortPokemonsByIdQuery
import com.montfel.pokfinder.core.network.SortPokemonsByNameQuery
import com.montfel.pokfinder.core.network.TypesQuery

fun TypeDto.toType(): Type {
    return Type(
        slot = slot,
        name = typeName?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun Type.toTypeDto(): TypeDto {
    return TypeDto(
        slot = slot,
        typeName = TypeNameDto(name = name)
    )
}

fun PokemonsQuery.Pokemon_v2_pokemontype.toTypeDto(): TypeDto {
    return TypeDto(
        slot = slot,
        typeName = TypeNameDto(name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() })
    )
}

fun PokemonsQuery.Pokemon_v2_pokemontype.toType(): Type {
    return Type(
        slot = slot,
        name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun SearchPokemonsQuery.Pokemon_v2_pokemontype.toType(): Type {
    return Type(
        slot = slot,
        name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun FilterPokemonsByTypesQuery.Pokemon_v2_pokemontype.toType(): Type {
    return Type(
        slot = slot,
        name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun FilterPokemonsByGenerationQuery.Pokemon_v2_pokemontype.toType(): Type {
    return Type(
        slot = slot,
        name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun SortPokemonsByNameQuery.Pokemon_v2_pokemontype.toType(): Type {
    return Type(
        slot = slot,
        name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun SortPokemonsByIdQuery.Pokemon_v2_pokemontype.toType(): Type {
    return Type(
        slot = slot,
        name = pokemon_v2_type?.name?.replaceFirstChar { it.uppercase() }
    )
}

fun TypesQuery.Pokemon_v2_type.toType(): Type {
    return Type(
        slot = null,
        name = name.replaceFirstChar { it.uppercase() },
    )
}

