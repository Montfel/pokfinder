package com.montfel.pokedex.data.dto

import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.model.PokemonHome

data class PokemonDto(
    val id: Int,
    val name: String,
    val types: List<TypesDto>
) : DtoMapper<PokemonHome> {
    override fun toDomain() = PokemonHome(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = types.map { it.toDomain() }
    )
}
