package com.montfel.pokedex.data.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Type

data class TypesDto(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeDto
) : DtoMapper<Type> {
    override fun toDomain() = Type(
        slot = slot,
        name = type.name.replaceFirstChar { it.uppercase() }
    )
}
