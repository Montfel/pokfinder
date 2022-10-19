package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.Types
import com.montfel.pokfinder.data.DtoMapper

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
