package com.montfel.pokedex.data.dto

import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.model.TypeEfficacy

data class TypeEfficacyDto(
    val damageFactor: Int,
    val name: String?
) : DtoMapper<TypeEfficacy> {
    override fun toDomain() = TypeEfficacy(
        damageFactor = damageFactor,
        name = name?.replaceFirstChar { it.uppercase() } ?: ""
    )
}

