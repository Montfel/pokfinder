package com.montfel.pokedex.data.profile.dto

import com.montfel.pokedex.data.dto.TypeDto
import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.model.Types

data class TypesDto(
    val slot: Int,
    val type: TypeDto
) : DtoMapper<Types> {
    override fun toDomain() = Types(
        slot = slot,
        type = type.toDomain()
    )
}
