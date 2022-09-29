package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.Stats
import com.montfel.pokfinder.data.DtoMapper
import kotlin.math.floor

data class StatsDto(
    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("effort")
    val effort: Int,

    @SerializedName("stat")
    val stat: StatDto

) : DtoMapper<Stats> {
    override fun toDomain() = Stats(
        baseStat = baseStat,
        effort = effort,
        stat = stat.toDomain(),
        min = if (stat.toDomain().name == "HP") baseStat * 2 + 110 else baseStat.times(2),
        max = if (stat.toDomain().name == "HP") baseStat * 2 + 204
        else floor(baseStat.times(2.2).plus(108.9)).toInt()
    )
}
