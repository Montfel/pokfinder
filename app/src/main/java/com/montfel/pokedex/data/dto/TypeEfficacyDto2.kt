package com.montfel.pokedex.data.dto

import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.model.TypeEfficacy2

// TODO: Verificar se esse arquivo é necessário
data class TypeEfficacyDto2(
    val damageFactor: Int,
    val name: String?
) : DtoMapper<TypeEfficacy2> {
    override fun toDomain() = TypeEfficacy2(
        damageFactor = damageFactor,
        name = name?.replaceFirstChar { it.uppercase() } ?: ""
    )
}

