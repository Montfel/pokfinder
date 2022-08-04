package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.helper.DtoMapper
import com.montfel.pokedex.domain.model.Types

data class TypesRestDto(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: TypeRestDto
) : DtoMapper<Types> {
    override fun toDomain() = Types(
        slot = slot,
        type = type.toDomain()
    )
}
