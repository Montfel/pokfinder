package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.PokemonList

data class PokemonListDto(
    @SerializedName("count")
    val count: Int,

    @SerializedName("next")
    val next: String,

    @SerializedName("previous")
    val previous: String,

    @SerializedName("results")
    val results: List<ResultDto>
) : DtoMapper<PokemonList> {
    override fun toDomain() = PokemonList(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toDomain() }
    )
}
