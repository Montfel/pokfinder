package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.TypesProfile
import com.montfel.pokedex.helper.DtoMapper

data class TypesDto(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: TypeProfileDto

) : DtoMapper<TypesProfile> {
    override fun toDomain() = TypesProfile(
        slot = slot,
        type = type.toDomain()
    )
}
