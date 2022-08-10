package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.data.DtoMapper

data class PokemonEvolutionChainDto(
    @SerializedName("chain")
    val chain: ChainDto

) : DtoMapper<PokemonEvolutionChain> {
    override fun toDomain() = PokemonEvolutionChain(
        chain = chain.toDomain()
    )
}
