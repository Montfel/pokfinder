package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.DamageType
import com.montfel.pokedex.data.DtoMapper

data class DamageTypeDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<DamageType> {
    override fun toDomain() = DamageType(
        name = name
    )
}
