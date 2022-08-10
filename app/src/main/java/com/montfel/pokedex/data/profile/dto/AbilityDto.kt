package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.data.DtoMapper
import com.montfel.pokedex.domain.profile.model.Ability

data class AbilityDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<Ability> {
    override fun toDomain() = Ability(
        name = name.replaceFirstChar { it.uppercase() }
    )
}
