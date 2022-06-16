package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Type

data class TypeRestDto(
    @SerializedName("name")
    val name: String,
) : DtoMapper<Type> {
    override fun toDomain() = Type(
        name = name.replaceFirstChar { it.uppercase() },
    )
}
