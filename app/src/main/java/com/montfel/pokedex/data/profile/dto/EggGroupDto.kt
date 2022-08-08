package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.EggGroup
import com.montfel.pokedex.helper.DtoMapper

data class EggGroupDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<EggGroup> {
    override fun toDomain() = EggGroup(
        name = name.replaceFirstChar { it.uppercase() }
    )
}
