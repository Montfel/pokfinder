package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Abilities
import com.montfel.pokedex.helper.DtoMapper

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
