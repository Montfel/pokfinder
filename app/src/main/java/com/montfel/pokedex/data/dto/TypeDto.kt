package com.montfel.pokedex.data.dto

import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.model.Type

data class TypeDto(
    val name: String?,
    val typeEfficacies: List<TypeEfficacyDto>? = emptyList()
) : DtoMapper<Type> {
    override fun toDomain() = Type(
        name = name?.replaceFirstChar { it.uppercase() } ?: "",
        typeEfficacies = typeEfficacies?.map { it.toDomain() }
    )
}
