package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.DamageType
import com.montfel.pokfinder.data.DtoMapper

data class DamageTypeDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<DamageType> {
    override fun toDomain() = DamageType(
        name = name
    )
}
