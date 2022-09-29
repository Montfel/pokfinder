package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.Abilities
import com.montfel.pokfinder.data.DtoMapper

data class AbilitiesDto(
    @SerializedName("ability")
    val ability: AbilityDto,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    @SerializedName("slot")
    val slot: Int

) : DtoMapper<Abilities> {
    override fun toDomain() = Abilities(
        ability = ability.toDomain(),
        isHidden = isHidden,
        slot = slot
    )
}
