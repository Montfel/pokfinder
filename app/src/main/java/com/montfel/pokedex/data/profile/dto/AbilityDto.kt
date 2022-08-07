package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Ability
import com.montfel.pokedex.helper.DtoMapper

data class AbilityDto(
    @SerializedName("name")
    val name: String

) : DtoMapper<Ability> {
    override fun toDomain() = Ability(
        name = name
    )
}
