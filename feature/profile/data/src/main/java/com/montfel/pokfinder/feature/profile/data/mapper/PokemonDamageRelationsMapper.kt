package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.PokemonDamageRelationsDto
import com.montfel.pokfinder.feature.profile.domain.model.PokemonDamageRelations

fun PokemonDamageRelationsDto.toPokemonDamageRelations(): PokemonDamageRelations {
    return PokemonDamageRelations(
        damageRelations = damageRelations?.toDamageRelations()
    )
}
