package com.montfel.pokedex.data

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.Result

data class ResultDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
) : DtoMapper<Result> {
    override fun toDomain() = Result(
        name = name.replaceFirstChar { it.uppercase() },
        url = url
            .substringAfter("pokemon/")
            .replace("/", "")
    )
}
