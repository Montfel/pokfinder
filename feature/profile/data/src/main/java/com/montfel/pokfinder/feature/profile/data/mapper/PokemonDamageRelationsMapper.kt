package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.PokemonDamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations

fun com.montfel.pokfinder.feature.profile.data.model.dto.PokemonDamageRelationsDto.toPokemonDamageRelations(): PokemonDamageRelations {
    return PokemonDamageRelations(
        damageRelations = damageRelations?.toDamageRelations()
    )
}
