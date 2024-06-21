package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.model.dto.PokemonDamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations

fun PokemonDamageRelationsDto.toPokemonDamageRelations(): PokemonDamageRelations {
    return PokemonDamageRelations(
        damageRelations = damageRelations?.toDamageRelations()
    )
}
