package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.Chain
import com.montfel.pokedex.data.DtoMapper

data class ChainDto(
    @SerializedName("evolves_to")
    val evolvesTo: List<Any>

) : DtoMapper<Chain> {
    override fun toDomain() = Chain(
        evolvesTo = evolvesTo
    )
}
