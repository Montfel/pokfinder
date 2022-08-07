package com.montfel.pokedex.data.profile.dto

import com.google.gson.annotations.SerializedName
import com.montfel.pokedex.domain.profile.model.PokemonDamageRelations
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.helper.DtoMapper

data class PokemonDamageRelationsDto(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelationsDto

) : DtoMapper<PokemonDamageRelations> {
    override fun toDomain() = PokemonDamageRelations(
        damageRelations = damageRelations.toDomain()
    )
}
