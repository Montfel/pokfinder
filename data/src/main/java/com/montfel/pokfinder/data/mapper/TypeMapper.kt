package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.FilterPokemonsByTypesQuery
import com.montfel.pokfinder.data.PokemonsQuery
import com.montfel.pokfinder.data.SearchPokemonsQuery
import com.montfel.pokfinder.data.TypesQuery
import com.montfel.pokfinder.data.model.dto.TypeDto
import com.montfel.pokfinder.data.model.dto.TypeNameDto
import com.montfel.pokfinder.domain.profile.model.Type

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

