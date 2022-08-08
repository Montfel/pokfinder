package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.DamageRelations
import com.montfel.pokedex.helper.DtoMapper

data class DamageRelationsDto(
    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<DamageTypeDto>,

    @SerializedName("double_damage_to")
    val doubleDamageTo: List<DamageTypeDto>,

    @SerializedName("half_damage_from")
    val halfDamageFrom: List<DamageTypeDto>,

    @SerializedName("half_damage_to")
    val halfDamageTo: List<DamageTypeDto>,

    @SerializedName("no_damage_from")
    val noDamageFrom: List<DamageTypeDto>,

    @SerializedName("no_damage_to")
    val noDamageTo: List<DamageTypeDto>

) : DtoMapper<DamageRelations> {
    override fun toDomain() = DamageRelations(
        doubleDamageFrom = doubleDamageFrom.map { it.toDomain().name }.sorted(),
        doubleDamageTo = doubleDamageTo.map { it.toDomain().name }.sorted(),
        halfDamageFrom = halfDamageFrom.map { it.toDomain().name }.sorted(),
        halfDamageTo = halfDamageTo.map { it.toDomain().name }.sorted(),
        noDamageFrom = noDamageFrom.map { it.toDomain().name }.sorted(),
        noDamageTo = noDamageTo.map { it.toDomain().name }.sorted()
    )
}
