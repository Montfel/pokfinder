package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Types
import com.montfel.pokedex.helper.DtoMapper

data class TypesDto(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: TypeProfileDto

) : DtoMapper<Types> {
    override fun toDomain() = Types(
        slot = slot,
        type = type.toDomain()
    )
}
