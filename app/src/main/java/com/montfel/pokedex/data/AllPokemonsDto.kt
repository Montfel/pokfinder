package com.montfel.pokedex.data

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.AllPokemons
import com.montfel.pokedex.domain.DtoMapper

data class AllPokemonsDto(
    @SerializedName("results")
    val results: List<ResultsDto>,
) : DtoMapper<AllPokemons> {
    override fun toDomain() = AllPokemons(
        results = results.map { it.name }
    )
}
