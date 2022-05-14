package com.montfel.pokedex.data

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.Results

data class ResultsDto(
    @SerializedName("name")
    val name: String,
) : DtoMapper<Results> {
    override fun toDomain() = Results(
        name = name
    )
}
