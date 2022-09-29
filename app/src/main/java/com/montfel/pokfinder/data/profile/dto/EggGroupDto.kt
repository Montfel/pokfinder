package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.EggGroup
import com.montfel.pokfinder.data.DtoMapper

data class EggGroupDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<EggGroup> {
    override fun toDomain() = EggGroup(
        name = name.replaceFirstChar { it.uppercase() }
    )
}
