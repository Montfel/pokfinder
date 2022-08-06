package com.montfel.pokedex.data.dto

import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.model.Type2

// TODO: Verificar se esse arquivo é necessário
data class TypeDto2(
    val name: String?,
    val typeEfficacies: List<TypeEfficacyDto2>? = emptyList()
) : DtoMapper<Type2> {
    override fun toDomain() = Type2(
        name = name?.replaceFirstChar { it.uppercase() } ?: "",
        typeEfficacies = typeEfficacies?.map { it.toDomain() }
    )
}
