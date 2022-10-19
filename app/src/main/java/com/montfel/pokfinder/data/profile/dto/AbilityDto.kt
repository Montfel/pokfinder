package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.data.DtoMapper
import com.montfel.pokfinder.domain.profile.model.Ability

data class AbilityDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<Ability> {
    override fun toDomain() = Ability(
        name = name.replaceFirstChar { it.uppercase() }
    )
}
