package com.montfel.pokfinder.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations
import com.montfel.pokfinder.data.DtoMapper
import com.montfel.pokfinder.data.profile.dto.DamageRelationsDto

data class PokemonDamageRelationsDto(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelationsDto

) : DtoMapper<PokemonDamageRelations> {
    override fun toDomain() = PokemonDamageRelations(
        damageRelations = damageRelations.toDomain()
    )
}
