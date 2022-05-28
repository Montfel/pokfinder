package com.montfel.pokedex.data.dto

import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Type

data class TypesDto(
    val slot: Int,
    val name: String?
) : DtoMapper<Type> {
    override fun toDomain() = Type(
        slot = slot,
        name = name?.replaceFirstChar { it.uppercase() }
    )
}
