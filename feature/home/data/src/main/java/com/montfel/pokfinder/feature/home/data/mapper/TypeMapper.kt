package com.montfel.pokfinder.feature.home.data.mapper

import com.montfel.core.database.model.dto.TypeDto
import com.montfel.core.database.model.dto.TypeNameDto
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.feature.home.data.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.feature.home.data.PokemonsQuery
import com.montfel.pokfinder.feature.home.data.SearchPokemonsQuery
import com.montfel.pokfinder.feature.home.data.TypesQuery

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

fun TypesQuery.Pokemon_v2_type.toType(): Type {
    return Type(
        slot = null,
        name = name.replaceFirstChar { it.uppercase() },
    )
}

