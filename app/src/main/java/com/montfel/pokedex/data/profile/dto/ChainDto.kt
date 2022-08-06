package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Ability
import com.montfel.pokedex.domain.profile.model.Chain
import com.montfel.pokedex.helper.DtoMapper

data class ChainDto(
    @SerializedName("evolution_details")
    val evolutionDetails: List<Any>,

    @SerializedName("evolves_to")
    val evolvesTo: List<Any>,

) : DtoMapper<Chain> {
    override fun toDomain() = Chain(
        evolutionDetails = evolutionDetails,
        evolvesTo = evolvesTo,
    )
}
