package com.montfel.pokedex.data.dto.rest

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper

data class ResultDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
) : DtoMapper<com.montfel.pokedex.domain.model.Result> {
    override fun toDomain() = com.montfel.pokedex.domain.model.Result(
        name = name,
        url = url
    )
}
